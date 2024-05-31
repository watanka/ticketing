package com.project1.ticketing.domain.reservation.repository;

import com.project1.ticketing.domain.reservation.models.Reservation;
import com.project1.ticketing.domain.reservation.models.ReservationStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReservationCoreRepository {


    Reservation save(Reservation reservation);

    Reservation findById(long reservationId);
    List<Reservation> findAllByUserId(long userId);

    List<Reservation> findAllByStatus(ReservationStatus status);
}
