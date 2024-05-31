package com.project1.ticketing.domain.reservation.repository;

import com.project1.ticketing.domain.reservation.models.Reservation;
import com.project1.ticketing.domain.reservation.models.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
    Reservation save(Reservation reservation);

    Reservation findById(long id);

    List<Reservation> findAllByUserId(long userId);

    List<Reservation> findAllByStatus(ReservationStatus status);

}
