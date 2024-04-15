package com.project1.ticketing.api.usecase;

import com.project1.ticketing.api.dto.response.ConcertResponse;

import com.project1.ticketing.domain.concert.components.MemoryConcertService;
import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ConcertUseCase {
    MemoryConcertService concertService;

    @Autowired
    public ConcertUseCase(MemoryConcertService concertService) {
        this.concertService = concertService;
    }

    public ConcertResponse getConcertList(){

        List<Concert> concertList = concertService.getConcertList();

        return new ConcertResponse(concertList);

    }

    public ConcertResponse getAvailableConcertTimeList(long concertId) {
        List<ConcertTime> availableConcertTimeList = concertService.getAllConcertTimeList(concertId);

        return new ConcertResponse(concertId, availableConcertTimeList);
    }

    public ConcertResponse getAvailableSeatList(long concertId, long concertTimeId) {
        List<Seat> availableSeatList = concertService.getAvailableSeatList(concertId, concertTimeId);

        return new ConcertResponse(concertId, concertTimeId, availableSeatList);

    }
}
