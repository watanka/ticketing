package com.project1.ticketing.domain.concert.infrastructure;

import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.repository.IConcertTimeRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.*;

@Repository
public class MemoryConcertTimeRepository implements IConcertTimeRepository {

    Map<Long, List<ConcertTime>> concertTimeMap = new HashMap<>(); // TODO: ConcertTime: String => ZonedDateTime


    public void save(long concertId, ConcertTime concertTime){
        concertTimeMap.get(concertId).add(concertTime);
    }
    @Override
    public List<ConcertTime> getAllByConcertId(long concertId) {
        return null;
    }

    @Override
    public List<ConcertTime> getAvailableTime(long concertId) {

        return concertTimeMap.getOrDefault(concertId, new ArrayList<>());
    }

    @Override
    public ConcertTime getByTime(long concertId, long concertTimeId) {
         return null;
    }

    @Override
    public Boolean isFull(long concertId, long concertTimeId) {
        return null;
    }
}
