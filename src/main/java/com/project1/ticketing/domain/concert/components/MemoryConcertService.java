package com.project1.ticketing.domain.concert.components;

import com.project1.ticketing.domain.concert.infrastructure.MemoryConcertRepository;
import com.project1.ticketing.domain.concert.infrastructure.MemoryConcertTimeRepository;
import com.project1.ticketing.domain.concert.infrastructure.MemorySeatRepository;
import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoryConcertService implements IConcertService {

    MemoryConcertRepository memoryConcertRepository;
    MemoryConcertTimeRepository memoryConcertTimeRepository;
    MemorySeatRepository memorySeatRepository;

    @Autowired
    public MemoryConcertService(MemoryConcertRepository memoryConcertRepository,
                                MemoryConcertTimeRepository memoryConcertTimeRepository,
                                MemorySeatRepository memorySeatRepository) {
        this.memoryConcertRepository = memoryConcertRepository;
        this.memoryConcertTimeRepository = memoryConcertTimeRepository;
        this.memorySeatRepository = memorySeatRepository;
    }

    public void addConcert(Concert concert){
        memoryConcertRepository.save(concert);
    }

    public void addConcertTime(long concertId, ConcertTime concertTime){
        memoryConcertTimeRepository.save(concertId, concertTime);
    }

    @Override
    public List<Concert> getConcertList() {
        return null;
    }

    @Override
    public List<ConcertTime> getAllConcertTimeList(long concertId) {
        return null;
    }

    @Override
    public List<Seat> getAvailableSeatList(long concertId, long concertTime) {
        return null;
    }
}
