package com.project1.ticketing.api.usecase;

import com.project1.ticketing.api.dto.response.ConcertResponseDTO;
import com.project1.ticketing.domain.concert.components.IConcertService;

import java.util.List;


public class ConcertUseCase {
    IConcertService concertService;
    public ConcertResponseDTO getConcertList(){

        List<String> concertList = concertService.getConcertList();

        return new ConcertResponseDTO(concertList);

    }

    public ConcertResponseDTO getAvailableConcertTimeList(long concertId) {
        List<String> availableConcertTimeList = concertService.getAvailableConcertTimeList(concertId);

        return new ConcertResponseDTO(concertId, availableConcertTimeList);
    }

    public ConcertResponseDTO getAvailableSeatList(long concertId, String concertTime) {
        List<Integer> availableSeatList = concertService.getAvailableSeatList(concertId, concertTime);

        return new ConcertResponseDTO(concertId, concertTime, availableSeatList);

    }
}
