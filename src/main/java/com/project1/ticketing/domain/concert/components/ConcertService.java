package com.project1.ticketing.domain.concert.components;

import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.repository.IConcertRepository;
import com.project1.ticketing.domain.concert.repository.IConcertTimeRepository;
import com.project1.ticketing.domain.concert.repository.ISeatRepository;

import java.util.List;

public class ConcertService implements IConcertService{

    IConcertRepository concertRepository;
    IConcertTimeRepository concertTimeRepository;
    ISeatRepository seatRepository;

    public ConcertService(IConcertRepository concertRepository, IConcertTimeRepository concertTimeRepository, ISeatRepository seatRepository) {
        this.concertRepository = concertRepository;
        this.concertTimeRepository = concertTimeRepository;
        this.seatRepository = seatRepository;
    }

    @Override
    public List<Concert> getConcertList() {
        return concertRepository.getAll();
    }

    @Override
    public List<ConcertTime> getAllConcertTimeList(long concertId) {
        return concertTimeRepository.getAllByConcertId(concertId);
    }

    @Override
    public List<Seat> getAvailableSeatList(long concertId, long concertTime) {
        return seatRepository.getAllByConcertAndConcertTime(concertId, concertTime);
    }
}
