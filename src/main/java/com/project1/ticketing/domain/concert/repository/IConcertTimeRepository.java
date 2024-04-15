package com.project1.ticketing.domain.concert.repository;

import com.project1.ticketing.domain.concert.models.ConcertTime;

import java.time.ZonedDateTime;
import java.util.List;

public interface IConcertTimeRepository {
    List<ConcertTime> getAllByConcertId(long concertId);
    List<ConcertTime> getAvailableTime(long concertId);
    ConcertTime getByTime(long concertId, long concertTimeId);
    Boolean isFull(long concertId, long concertTimeId);
}
