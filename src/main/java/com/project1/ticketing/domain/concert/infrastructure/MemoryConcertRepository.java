package com.project1.ticketing.domain.concert.infrastructure;

import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.repository.IConcertRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public class MemoryConcertRepository implements IConcertRepository{
    Map<Long, Concert> concertMap = new HashMap<>();

    public void save(Concert concert){
        concertMap.put(concert.getId(),  concert);
    }

    @Override
    public List<Concert> getAll() {
        return concertMap.values().stream().toList();
    }



    @Override
    public Optional<Concert> findById(long concertId) {
        return Optional.of(concertMap.get(concertId));
    }

    @Override
    public boolean isFull(long concertId) {
        return false;
    }
}
