package com.project1.ticketing.domain.reservation.components;

import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.point.models.User;
import com.project1.ticketing.domain.reservation.infrastructure.MemoryReservationRepository;
import com.project1.ticketing.domain.reservation.models.Reservation;
import com.project1.ticketing.domain.reservation.models.ReservationStatus;
import com.project1.ticketing.domain.reservation.repository.IReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public class ReservationService {

    IReservationRepository reservationRepository;
    ReservationValidator reservationValidator;

    public ReservationService(IReservationRepository reservationRepository, ReservationValidator reservationValidator) {
        this.reservationRepository = reservationRepository;
        this.reservationValidator = reservationValidator;
    }

    public void register(User user, long concertTimeId, long seatId){
        reservationValidator.validate(user.getId(), concertTimeId, seatId);

        Reservation reservation = Reservation.builder()
                .user(user)
                .concertTimeId(concertTimeId)
                .seatId(seatId)
                .build();

        reservationRepository.save(reservation);
    }

    public boolean checkReserved(long concertTimeId, long seatId){
        return reservationRepository.findByConcertTimeIdAndSeatId(concertTimeId, seatId).isPresent();
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

}
