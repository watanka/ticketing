package com.project1.ticketing.domain.concert.infrastructure;

import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.repository.ISeatRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemorySeatRepository implements ISeatRepository {
    @Override
    public boolean isAvailable(long concertId, long concertTimeId, long seatId) {
        return false;
    }

    @Override
    public Optional<Seat> findById(long concertId, long concertTimeId, long seatId) {
        return Optional.empty();
    }

    @Override
    public List<Seat> getAllSeats(long concertHallId) {
        return null;
    }
}
