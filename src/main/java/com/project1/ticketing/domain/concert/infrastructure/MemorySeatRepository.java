package com.project1.ticketing.domain.concert.infrastructure;

import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.repository.ISeatRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemorySeatRepository implements ISeatRepository {

    @Override
    public boolean isAvailable(long seatId) {
        return false;
    }

    @Override
    public Optional<Seat> findById(long seatId) {
        return Optional.empty();
    }

    @Override
    public List<Seat> getByConcertTimeId(long concertTimeId) {
        return null;
    }


    @Override
    public Optional<Seat> getAvailableSeatsbyConcertTimeId(long concertTimeId) {
        return Optional.empty();
    }
}
