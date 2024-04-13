package com.project1.ticketing.domain.concert.repository;

import com.project1.ticketing.domain.concert.models.ConcertTime;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface IConcertTimeRepository {
    List<ConcertTime> getAllByConcertId(long concertId);
    List<ConcertTime> getAvailableTimeByConcertId(long concertId);
    List<ConcertTime> getByTime(ZonedDateTime dateTime);
    Boolean isFull(ZonedDateTime dateTime);
}
