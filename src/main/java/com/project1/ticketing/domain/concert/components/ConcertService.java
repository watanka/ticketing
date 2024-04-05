package com.project1.ticketing.domain.concert.components;

import com.project1.ticketing.api.dto.response.ConcertResponseDTO;
import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.repository.ConcertRepository;
import com.project1.ticketing.domain.concert.repository.ConcertTimeRepository;
import com.project1.ticketing.domain.concert.repository.SeatRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public class ConcertService implements IConcertService{ // 우선 서비스로 정의하고, 필요하면 기능 나누기

    ConcertRepository concertRepository;
    ConcertTimeRepository concertTimeRepository;
    SeatRepository seatRepository;

    public ConcertService(ConcertRepository concertRepository, ConcertTimeRepository concertTimeRepository, SeatRepository seatRepository) {
        this.concertRepository = concertRepository;
        this.concertTimeRepository = concertTimeRepository;
        this.seatRepository = seatRepository;
    }

    @Override
    public List<ConcertResponseDTO> getConcert() {
        return null;
    }

    @Override
    public ConcertResponseDTO getConcertDate(long concertId) {
        return null;
    }

    @Override
    public List<ConcertResponseDTO> getAllSeat(long concertId, long concertTimeId) {
        return null;
    }

}