package com.project1.ticketing.domain.concert.components;

import com.project1.ticketing.api.dto.response.ConcertResponseDTO;

import java.util.List;

public interface IConcertService {

    ConcertResponseDTO getConcertList();
    ConcertResponseDTO getAvailableConcertTime(long concertId);
    ConcertResponseDTO getAvailableSeat(long concertId, String concertTime);
}
