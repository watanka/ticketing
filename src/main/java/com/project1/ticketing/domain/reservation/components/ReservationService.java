package com.project1.ticketing.domain.reservation.components;

import com.project1.ticketing.api.dto.request.ReservationRequest;
import com.project1.ticketing.api.dto.response.ConcertResponse;
import com.project1.ticketing.api.dto.response.ReservationResponse;
import com.project1.ticketing.domain.concert.components.ConcertService;
import com.project1.ticketing.domain.concert.infrastructure.ConcertCoreRepositoryImpl;
import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.repository.ConcertCoreRepository;
import com.project1.ticketing.domain.concert.repository.SeatJpaRepository;
import com.project1.ticketing.domain.point.models.User;
import com.project1.ticketing.domain.point.repository.UserJpaRepository;
import com.project1.ticketing.domain.reservation.infrastructure.ReservationCoreRepositoryImpl;
import com.project1.ticketing.domain.reservation.models.Reservation;
import com.project1.ticketing.domain.reservation.models.ReservationStatus;
import com.project1.ticketing.domain.reservation.repository.ReservationCoreRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReservationService implements IReservationService {

    ReservationCoreRepositoryImpl reservationRepository;
    ReservationValidator reservationValidator;

    ConcertCoreRepository concertRepository;

    UserJpaRepository userRepository;

    public ReservationService(ReservationCoreRepositoryImpl reservationRepository,
                              ReservationValidator reservationValidator,
                              ConcertCoreRepository concertRepository,
                              UserJpaRepository userRepository
                              ) {
        this.reservationRepository = reservationRepository;
        this.reservationValidator = reservationValidator;
        this.concertRepository = concertRepository;
        this.userRepository = userRepository;
    }

    public ReservationResponse register(ReservationRequest request){

        long userId = request.getUserId();
        long seatId = request.getSeatId();
        String concertTIme = request.getConcertTime();


        // reservation 검증
        // 좌석 예약 가능 확인
        // 예약 생성
        // 예약 저장 reservationRepository.save
        // 좌석 상태 변경
        // 좌석 상태 저장 seatRepository.save
        // 예약 및 좌석 상태 관리 이벤트 발행
        reservationValidator.validateSeat(seatId);

        Seat seat = concertRepository.findSeatById(seatId)
                .orElseThrow();

        Reservation reservation = Reservation.builder()
                .userId(userId)
                .concertTime(ZonedDateTime.parse(concertTIme))
                .seatNum(seat.getSeatNum())
                .price(seat.getPrice())
                .status(ReservationStatus.TEMPORARY)
                .createAt(ZonedDateTime.now())
                .build();

        reservationRepository.save(reservation);
        concertRepository.saveSeat(seat);

        //TODO: 5분 후 예약대기 상태를 업데이트하는 이벤트 발행

        return ReservationResponse.from(reservation);
    }

    public Reservation updateReservationStatus(long reservationId){

        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if (reservation.isEmpty()){
            throw new RuntimeException("예약을 찾을 수 없습니다.");
        }
        Reservation selectedReservation = reservation.get();

        ZonedDateTime reservationExpiredTime = selectedReservation.getCreateAt().plusMinutes(5);


        if (ZonedDateTime.now().isAfter(reservationExpiredTime)){
            selectedReservation.setStatus(ReservationStatus.CANCELLED);
            reservationRepository.save(selectedReservation);
        }
        return selectedReservation;
    }

    public List<Reservation> findAllByUserId(long userId){
        return reservationRepository.findAllByUserId(userId);
    }

    public Optional<Reservation> findByReservationId(long reservationId){
        return reservationRepository.findById(reservationId);
    }

    @Override
    public List<ReservationResponse> checkReservationList(long userId) {
        // userId 검증
        Optional<User> user = userRepository.findById(userId);
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
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()){
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }
        // reservationId 검증
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(); // new RuntimeException("해당 예약을 찾을 수 없습니다.")

        return ReservationResponse.from(reservation);
    }
}
