package com.project1.ticketing.domain.concert.components;

import com.project1.ticketing.api.dto.response.ConcertResponseDTO;

import java.util.List;

public interface IConcertService {

    List<ConcertResponseDTO> getConcert();
    ConcertResponseDTO getConcertDate(long concertId);
    List<ConcertResponseDTO> getAllSeat(long concertId, long concertTimeId);
}
