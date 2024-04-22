package com.project1.ticketing.domain.concert.repository;

import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.models.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatJpaRepository extends JpaRepository<Seat, Long> {
    Seat save(Seat seat);
    Optional<Seat> findById(long seatId);
    List<Seat> findAllByConcertTimeId(long concertTimeId);
    boolean existsByConcertTimeIdAndStatus(long concertTimeId, SeatStatus status);

    Optional<Seat> findByIdAndStatus(long seatId, SeatStatus status);
}
