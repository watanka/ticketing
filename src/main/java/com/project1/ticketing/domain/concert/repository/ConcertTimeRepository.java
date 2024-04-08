package com.project1.ticketing.domain.concert.repository;

import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface ConcertTimeRepository {
    List<ConcertTime> getAllByConcertId(long concertId);
    Optional<ConcertTime> getAvailableTimeByConcertId(long concertId);
    Optional<ConcertTime> getByTime(ZonedDateTime dateTime);
    Boolean isFull(ZonedDateTime dateTime);
}
