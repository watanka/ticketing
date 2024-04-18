package com.project1.ticketing.domain.concert.repository;

import com.project1.ticketing.domain.concert.models.Concert;

import java.util.List;
import java.util.Optional;

public interface IConcertRepository {
    List<Concert> getAll();
    Optional<Concert> findById(long concertId);

}