package com.project1.ticketing.domain.reservation.repository;

import com.project1.ticketing.domain.reservation.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
    Reservation save(Reservation reservation);

    Reservation findById(long id);

    List<Reservation> findAllByUserId(long userId);

    Optional<Reservation> findBySeatId(long seatId);
}
