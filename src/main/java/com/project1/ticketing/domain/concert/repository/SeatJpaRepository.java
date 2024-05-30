package com.project1.ticketing.domain.concert.repository;

import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.models.SeatStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

import static jakarta.persistence.LockModeType.OPTIMISTIC_FORCE_INCREMENT;

public interface SeatJpaRepository extends JpaRepository<Seat, Long> {

    @Lock(OPTIMISTIC_FORCE_INCREMENT)
    Seat save(Seat seat);

    Seat findById(long seatId);
    List<Seat> findAllByConcertTimeId(long concertTimeId);
    boolean existsByConcertTimeIdAndStatus(long concertTimeId, SeatStatus status);

    Seat findSeatByConcertTimeIdAndSeatNum(long concertTimeId, long seatNum);
    Optional<Seat> findByIdAndStatus(long seatId, SeatStatus status);
}

