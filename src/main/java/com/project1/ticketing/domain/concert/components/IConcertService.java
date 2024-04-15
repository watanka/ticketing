package com.project1.ticketing.domain.concert.components;

import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;

import java.util.List;

public interface IConcertService {


    List<Concert> getConcertList();
    List<ConcertTime> getAllConcertTimeList(long concertId);
    List<Seat> getAvailableSeatList(long concertId, long concertTime);
}
