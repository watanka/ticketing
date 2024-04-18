package com.project1.ticketing.domain.concert.repository;

import com.project1.ticketing.domain.concert.models.ConcertTime;

import java.util.List;
import java.util.Optional;

public interface IConcertTimeRepository {
    List<ConcertTime> getAllByConcertId(long concertId);
    Optional<ConcertTime> findById(long concertTimeId);

}
