package com.project1.ticketing.domain.concert.components;

import com.project1.ticketing.domain.concert.infrastructure.MemoryConcertRepository;
import com.project1.ticketing.domain.concert.infrastructure.MemoryConcertTimeRepository;
import com.project1.ticketing.domain.concert.infrastructure.MemorySeatRepository;
import com.project1.ticketing.domain.concert.models.Concert;
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

    public void addConcertTime(long concertId, String concertTime){
        memoryConcertTimeRepository.save(concertId, concertTime);
    }

    @Override
    public List<String> getConcertList() {
        return null;
    }

    @Override
    public List<String> getAvailableConcertTimeList(long concertId) {
        return null;
    }

    @Override
    public List<Integer> getAvailableSeatList(long concertId, String concertTime) {
        return null;
    }
}
