package com.project1.ticketing.domain.concert.repository;

import com.project1.ticketing.domain.concert.models.Concert;

import java.util.List;

public interface IConcertRepository {
    List<Concert> getAll();
    Concert getById(long concertId);

    boolean isFull(long concertId);

}