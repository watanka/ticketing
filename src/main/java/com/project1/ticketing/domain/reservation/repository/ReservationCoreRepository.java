package com.project1.ticketing.domain.reservation.repository;

import com.project1.ticketing.domain.reservation.models.Reservation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReservationCoreRepository {


    Reservation save(Reservation reservation);
    Optional<Reservation> findById(long reservationId);
    List<Reservation> findAllByUserId(long userId);

    Optional<Reservation> findBySeatId(long seatId);

}
