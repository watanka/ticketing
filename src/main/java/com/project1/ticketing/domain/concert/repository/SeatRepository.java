package com.project1.ticketing.domain.concert.repository;
import com.project1.ticketing.domain.concert.models.Seat;

import java.util.Optional;

public interface SeatRepository { // seat는 concertTime에 대한 정보를 모른다.
    boolean isAvailable(long seatId);
    Optional<Seat> findById(long seatId);

    Optional<Seat> getAllByConcertAndConcertDate(long concertId, long concertDateId);
}
