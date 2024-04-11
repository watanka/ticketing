package com.project1.ticketing.domain.concert.repository;
import com.project1.ticketing.domain.concert.models.Seat;

import java.util.List;
import java.util.Optional;

public interface ISeatRepository { // seat는 concertTime에 대한 정보를 모른다.
    boolean isAvailable(long seatId);
    Optional<Seat> findById(long seatId);

    List<Seat> getAllByConcertAndConcertTime(long concertId, long  concertTimeId);
}