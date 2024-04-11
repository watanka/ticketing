package com.project1.ticketing.domain.concert.components;

import com.project1.ticketing.api.dto.response.ConcertResponseDTO;
import com.project1.ticketing.domain.concert.models.Concert;

import java.util.List;

public interface IConcertService {


    List<String> getConcertList();
    List<String> getAvailableConcertTimeList(long concertId);
    List<Integer> getAvailableSeatList(long concertId, String concertTime);
}
