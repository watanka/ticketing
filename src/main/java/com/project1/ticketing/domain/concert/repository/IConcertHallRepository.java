package com.project1.ticketing.domain.concert.repository;

import com.project1.ticketing.domain.concert.models.ConcertHall;

import java.util.List;
import java.util.Optional;

public interface IConcertHallRepository {

    List<ConcertHall> getAll();
    Optional<ConcertHall> findById(long hallId);


}


