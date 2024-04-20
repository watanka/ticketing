package com.project1.ticketing.api.controller.mock;

import com.project1.ticketing.api.dto.response.ConcertResponse;
import com.project1.ticketing.api.dto.response.ConcertTimeResponse;
import com.project1.ticketing.api.dto.response.SeatResponse;
import com.project1.ticketing.domain.concert.components.IConcertService;
import org.springframework.stereotype.Service;

import java.util.List;


//@Service
public class FakeConcertService implements IConcertService {

    @Override
    public List<ConcertResponse> getAllConcerts(){
        return List.of(
                new ConcertResponse(0L, "나훈아50주년콘서트"),
                new ConcertResponse(1L, "아일릿데뷔콘서트"),
                new ConcertResponse(2L, "아이유드림콘서트")
        );
    }

    @Override
    public List<ConcertTimeResponse> getAvailableTimes(long concertId){
        return List.of(
                ConcertTimeResponse.builder()
                        .id(0L)
                        .time(ConcertTimeResponse.fromStr("2024/04/20/ 10:00:00 KST"))
                        .concertHallId(0L)
                        .maxSeatNum(50)
                        .currAvailableSeatNum(50)
                        .build(),
                ConcertTimeResponse.builder()
                        .id(0L)
                        .time(ConcertTimeResponse.fromStr("2024/05/20/ 10:00:00 KST"))
                        .concertHallId(0L)
                        .maxSeatNum(50)
                        .currAvailableSeatNum(50)
                        .build()
        );
    }

    @Override
    public List<SeatResponse> getAvailableSeats(long concertTimeId){

        return List.of(
                SeatResponse.builder()
                        .id(0L)
                        .seatNum(30L)
                        .concertHallId(0L)
                        .price(30000L)
                        .available(true)
                        .build(),
                SeatResponse.builder()
                        .id(1L)
                        .seatNum(31L)
                        .concertHallId(0L)
                        .price(30000L)
                        .available(false)
                        .build(),
                SeatResponse.builder()
                        .id(2L)
                        .seatNum(32L)
                        .concertHallId(0L)
                        .price(50000L)
                        .available(false)
                        .build()
        );
    }



}
