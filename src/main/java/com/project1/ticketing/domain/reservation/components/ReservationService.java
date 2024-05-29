package com.project1.ticketing.domain.reservation.components;

import com.project1.ticketing.api.dto.request.ReservationRequest;
import com.project1.ticketing.api.dto.response.ReservationResponse;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.models.SeatStatus;
import com.project1.ticketing.domain.concert.repository.ConcertCoreRepository;
import com.project1.ticketing.domain.concert.repository.SeatJpaRepository;
import com.project1.ticketing.domain.point.components.UserManager;
import com.project1.ticketing.domain.point.models.User;
import com.project1.ticketing.domain.reservation.event.ReservationEvent;
import com.project1.ticketing.domain.reservation.event.ReservationEventPublisher;
import com.project1.ticketing.domain.reservation.models.Reservation;
import com.project1.ticketing.domain.reservation.models.ReservationStatus;
import com.project1.ticketing.domain.reservation.repository.ReservationCoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.transaction.TransactionDefinition.*;

@Service
@AllArgsConstructor
public class ReservationService implements IReservationService {

    ReservationCoreRepository reservationRepository;
    ReservationValidator reservationValidator;

    ConcertCoreRepository concertRepository;
    UserManager userManager;

    ReservationEventPublisher reservationEventPublisher;


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ReservationResponse reserve(ReservationRequest request){

        long userId = request.userId();
        long seatId = request.seatId();
        long concertTimeId = request.concertTimeId();

        System.out.println("콘서트 시간 확인");
        ConcertTime concertTime = concertRepository.findConcertTimeById(concertTimeId);

        System.out.println("좌석 상태 검증 시작");
//        reservationValidator.validateSeat(seatId);
        System.out.println("좌석 조회");
        Seat seat = concertRepository.findSeatById(seatId);

        seat.changeStatus(SeatStatus.RESERVED);
        concertRepository.saveSeat(seat);

        System.out.println("좌석 상태 변경");

        System.out.println("예약 생성");
        Reservation reservation = Reservation.builder()
                .userId(userId)
                .concertTimeId(concertTime.getId())
                .seatNum(seat.getSeatNum())
                .seatId(seatId)
                .price(seat.getPrice())
                .expiredAt(ZonedDateTime.now().plusMinutes(5))
                .build();

        reservationRepository.save(reservation);

        return ReservationResponse.from(reservation);
    }

    public void updateReservationStatus(){
        List<Reservation> reservationList = reservationRepository.findAllByStatus(ReservationStatus.TEMPORARY);

        for (Reservation reservation : reservationList) {
            if (ZonedDateTime.now().isAfter(reservation.getExpiredAt())){

                // 예약 상태 변경
                updateSingleReservationStatus(reservation, ReservationStatus.CANCELLED);

                // 좌석 상태 변경
                long seatId = reservation.getSeatId();
                Seat foundSeat = concertRepository.findSeatById(seatId);
                foundSeat.changeStatus(SeatStatus.AVAILABLE);
                concertRepository.saveSeat(foundSeat);
            }
        }
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
        User user = userManager.findById(userId);

        return reservationRepository.findAllByUserId(userId).stream()
                .map(ReservationResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationResponse check(long userId, long reservationId) {
        // userId 검증
        User user = userManager.findById(userId);
        // reservationId 검증
        Reservation reservation = reservationRepository.findById(reservationId);

        return ReservationResponse.from(reservation);

    }

    @Override
    public boolean checkSeatReserved(long seatId) {

        Seat seat =  concertRepository.findSeatById(seatId);
        return seat.getStatus().toBoolean();
    }

    @Override
    public ReservationResponse cancel(long userId, long reservationId) {

        // 예약 확인
        Reservation reservation = reservationRepository.findById(reservationId);
        // 좌석 확인
        Seat seat = concertRepository.findSeatById(reservation.getSeatId());
        // 예약 상태 변경
        reservation.setStatus(ReservationStatus.CANCELLED);
        reservationRepository.save(reservation);
        // 좌석 상태 변경
        seat.changeStatus(SeatStatus.AVAILABLE);
        concertRepository.saveSeat(seat);

        return ReservationResponse.from(reservation);
    }

    public void updateSingleReservationStatus(Reservation reservation, ReservationStatus status){
        reservation.setStatus(status);
        reservationRepository.save(reservation);
    }


}
