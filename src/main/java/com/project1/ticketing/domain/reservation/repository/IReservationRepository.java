package com.project1.ticketing.domain.reservation.repository;

import com.project1.ticketing.domain.reservation.models.Reservation;
import com.project1.ticketing.domain.reservation.models.ReservationStatus;

import java.util.List;
import java.util.Optional;


public interface IReservationRepository {

    Optional<Reservation> findById(long reservationId);
    List<Reservation> findAllByUserId(long userId);

    Optional<Reservation> findByConcertTimeIdAndSeatId(long concertTimeId, long seatId);

    void save(Reservation reservation);
}
