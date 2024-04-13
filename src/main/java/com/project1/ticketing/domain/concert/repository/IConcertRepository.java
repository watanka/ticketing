package com.project1.ticketing.domain.concert.repository;

import com.project1.ticketing.domain.concert.models.Concert;

import java.util.List;

public interface IConcertRepository { // 쓸일은 없을듯?
    List<Concert> getAll();
    Concert getById(long concertId);
}