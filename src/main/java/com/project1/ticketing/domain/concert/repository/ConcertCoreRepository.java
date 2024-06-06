package com.project1.ticketing.domain.concert.repository;

import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;

import java.util.List;
import java.util.Optional;

public interface ConcertCoreRepository {


    void deleteAll();
    // save
    Concert saveConcert(Concert concert);
    ConcertTime saveConcertTime(ConcertTime concertTime);

    Seat saveSeat(Seat seat);

    Seat findSeatByConcertTimeIdAndSeatNum(long concertTimeId, long seatNum);
    // read

    List<Concert> findAllConcerts();

    Concert findConcertById(long concertId);

    ConcertTime findConcertTimeById(long concertTimeId);

    public Seat findSeatById(long seatId);


    // read List

    List<ConcertTime> findAllConcertTimesByConcertId(long concertId);

    public List<Seat> findAllSeatsByConcertTimeId(long concertTimeId);

    public boolean isconcertTimeAvailable(long concertTimeId);

}