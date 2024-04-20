package com.project1.ticketing.domain.reservation.repository;

import com.project1.ticketing.domain.reservation.models.Reservation;

import java.util.List;
import java.util.Optional;


public interface ReservationCoreRepository {


    Reservation save(Reservation reservation);
    Optional<Reservation> findById(long reservationId);
    List<Reservation> findAllByUserId(long userId);

    Optional<Reservation> findByConcertTimeIdAndSeatId(long concertTimeId, long seatId);

}
