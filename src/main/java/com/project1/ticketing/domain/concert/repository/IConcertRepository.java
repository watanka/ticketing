package com.project1.ticketing.domain.concert.repository;

import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.models.SeatStatus;

import java.util.List;
import java.util.Optional;

public interface IConcertRepository {
    List<Concert> findAll();
    Optional<Concert> findConcertById(long concertId);

    List<ConcertTime> findAllByConcertId(long concertId);
    Optional<ConcertTime> findConcertTimeById(long concertTimeId);

    public List<Seat> findAllSeatByConcertTimeId(long concertTimeId);

    public boolean isconcertTimeAvailable(long concertTimeId, SeatStatus status);

}