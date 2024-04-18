package com.project1.ticketing.domain.concert.repository;
import com.project1.ticketing.domain.concert.models.Seat;

import java.util.List;
import java.util.Optional;

public interface ISeatRepository { // seat는 concertTime에 대한 정보를 모른다.
    public boolean isAvailable(long seatId);
    public Optional<Seat> findById(long seatId);

    public List<Seat> getByConcertTimeId(long concertTimeId);

    public Optional<Seat> getAvailableSeatsbyConcertTimeId(long concertTimeId);
}