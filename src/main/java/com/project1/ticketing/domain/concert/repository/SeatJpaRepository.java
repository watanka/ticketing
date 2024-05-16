package com.project1.ticketing.domain.concert.repository;

import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.models.SeatStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface SeatJpaRepository extends JpaRepository<Seat, Long> {

    Seat save(Seat seat);
    Seat findById(long seatId);
    List<Seat> findAllByConcertTimeId(long concertTimeId);
    boolean existsByConcertTimeIdAndStatus(long concertTimeId, SeatStatus status);

    Optional<Seat> findByIdAndStatus(long seatId, SeatStatus status);
}

