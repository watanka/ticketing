package com.project1.ticketing.api.usecase;

import com.project1.ticketing.api.dto.response.ConcertResponseDTO;

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

    public ConcertResponseDTO getConcertList(){

        List<Concert> concertList = concertService.getConcertList();

        return new ConcertResponseDTO(concertList);

    }

    public ConcertResponseDTO getAvailableConcertTimeList(long concertId) {
        List<ConcertTime> availableConcertTimeList = concertService.getAllConcertTimeList(concertId);

        return new ConcertResponseDTO(concertId, availableConcertTimeList);
    }

    public ConcertResponseDTO getAvailableSeatList(long concertId, long concertTimeId) {
        List<Seat> availableSeatList = concertService.getAvailableSeatList(concertId, concertTimeId);

        return new ConcertResponseDTO(concertId, concertTimeId, availableSeatList);

    }
}
