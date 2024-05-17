package com.project1.ticketing.domain.reservation.components;

import com.project1.ticketing.api.dto.request.ReservationRequest;
import com.project1.ticketing.api.dto.response.ReservationResponse;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.models.SeatStatus;
import com.project1.ticketing.domain.concert.repository.ConcertCoreRepository;
import com.project1.ticketing.domain.point.components.UserManager;
import com.project1.ticketing.domain.point.models.User;
import com.project1.ticketing.domain.reservation.event.ReservationEvent;
import com.project1.ticketing.domain.reservation.event.ReservationEventPublisher;
import com.project1.ticketing.domain.reservation.models.Reservation;
import com.project1.ticketing.domain.reservation.models.ReservationStatus;
import com.project1.ticketing.domain.reservation.repository.ReservationCoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService implements IReservationService {

    ReservationCoreRepository reservationRepository;
    ReservationValidator reservationValidator;

    ConcertCoreRepository concertRepository;

    UserManager userManager;

    ReservationEventPublisher reservationEventPublisher;

    @Autowired
    public ReservationService(ReservationCoreRepository reservationRepository,
                              ReservationValidator reservationValidator,
                              ConcertCoreRepository concertRepository,
                              UserManager userManager
                              ) {
        this.reservationRepository = reservationRepository;
        this.reservationValidator = reservationValidator;
        this.concertRepository = concertRepository;
        this.userManager = userManager;
    }

    @Transactional
    public ReservationResponse reserve(ReservationRequest request){

        long userId = request.getUserId();
        long seatId = request.getSeatId();
        long concertTimeId = request.getConcertTimeId();

        System.out.println("콘서트 시간 확인");
        ConcertTime concertTime = concertRepository.findConcertTimeById(concertTimeId);


        // reservation 검증
        // 좌석 예약 가능 확인
        // 예약 생성
        // 예약 저장 reservationRepository.save
        // 좌석 상태 변경
        // 좌석 상태 저장 seatRepository.save
        // 예약 및 좌석 상태 관리 이벤트 발행
        System.out.println("좌석 상태 검증 시작");
        reservationValidator.validateSeat(seatId);

        System.out.println("좌석 조회");
        Seat seat = concertRepository.findSeatById(seatId);
        System.out.println("좌석 상태 변경");
        seat.changeStatus(SeatStatus.RESERVED);

        System.out.println("예약 생성");
        Reservation reservation = Reservation.builder()
                .userId(userId)
                .concertTime(concertTime.getTime().toString())
                .seatNum(seat.getSeatNum())
                .seatId(seatId)
                .price(seat.getPrice())

                .status(ReservationStatus.TEMPORARY)
                .createAt(ZonedDateTime.now())
                .build();



        //TODO: 5분 후 예약대기 상태를 업데이트하는 이벤트 발행
        reservationEventPublisher.reservationOccupy(new ReservationEvent(this, reservation.getId()));

        return ReservationResponse.from(reservation);
    }

    public Reservation updateReservationStatus(long reservationId){

        Reservation reservation = reservationRepository.findById(reservationId);

        ZonedDateTime reservationExpiredTime = reservation.getCreateAt().plusMinutes(5);


        if (ZonedDateTime.now().isAfter(reservationExpiredTime)){
            reservation.setStatus(ReservationStatus.CANCELLED);
            reservationRepository.save(reservation);
        }
        return reservation;
    }

    public List<Reservation> findAllByUserId(long userId){
        return reservationRepository.findAllByUserId(userId);
    }

    public Reservation findByReservationId(long reservationId){
        return reservationRepository.findById(reservationId);
    }

    @Override
    public List<ReservationResponse> checkReservationList(long userId) {
        // userId 검증
        Optional<User> user = userManager.findById(userId);
        if (user.isEmpty()){
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }

        return reservationRepository.findAllByUserId(userId).stream()
                .map(ReservationResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationResponse check(long userId, long reservationId) {
        // userId 검증
        Optional<User> user = userManager.findById(userId);
        if (user.isEmpty()){
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }
        // reservationId 검증
        Reservation reservation = reservationRepository.findById(reservationId);

        return ReservationResponse.from(reservation);
    }

    @Override
    public boolean checkSeatReserved(long seatId) {

        Seat seat =  concertRepository.findSeatById(seatId);
        return seat.getStatus().toBoolean();
    }


}
