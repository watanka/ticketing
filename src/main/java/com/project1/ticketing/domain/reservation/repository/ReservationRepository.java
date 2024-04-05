package com.project1.ticketing.domain.reservation.repository;

import com.project1.ticketing.domain.reservation.models.Reservation;

import java.util.Optional;

public interface ReservationRepository {

    Optional<Reservation> getById(long reservationId);
    void save(Reservation reservation);
}
