package com.project1.ticketing.domain.concert.components;

import com.project1.ticketing.api.dto.response.ConcertResponse;
import com.project1.ticketing.api.dto.response.ConcertTimeResponse;
import com.project1.ticketing.api.dto.response.SeatResponse;
import com.project1.ticketing.domain.concert.models.SeatStatus;

import java.util.List;

public interface IConcertService {
    public List<ConcertResponse> getAllConcerts();
    public List<ConcertTimeResponse> getAvailableTimes(long concertId);
    public List<SeatResponse> getAvailableSeats(long concertTimeId);

    public void patchSeatStatus(long seatId, SeatStatus status);
}
