package com.project1.ticketing.domain.concert.infrastructure;

import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.repository.IConcertTimeRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryConcertTimeRepository implements IConcertTimeRepository {

    Map<Long, List<String>> concertTimeMap = new HashMap<>(); // TODO: ConcertTime: String => ZonedDateTime


    public void save(long concertId, String concertTime){
        concertTimeMap.get(concertId).add(concertTime);
    }
    @Override
    public List<ConcertTime> getAllByConcertId(long concertId) {
        return null;
    }

    @Override
    public Optional<ConcertTime> getAvailableTimeByConcertId(long concertId) {
        return Optional.empty();
    }

    @Override
    public Optional<ConcertTime> getByTime(ZonedDateTime dateTime) {
        return Optional.empty();
    }

    @Override
    public Boolean isFull(ZonedDateTime dateTime) {
        return null;
    }
}
