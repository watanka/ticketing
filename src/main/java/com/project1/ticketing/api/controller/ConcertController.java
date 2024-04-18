package com.project1.ticketing.api.controller;

import com.project1.ticketing.api.dto.response.ConcertResponse;
import com.project1.ticketing.api.dto.response.ConcertTimeResponse;
import com.project1.ticketing.api.dto.response.SeatResponse;
import com.project1.ticketing.domain.concert.components.ConcertService;
import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.models.SeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConcertController {

    ConcertService concertService;

//    @Autowired
//    public ConcertController(ConcertService concertService) {
//        this.concertService = concertService;
//    }

    @GetMapping("/concerts")
    public ResponseEntity<List<ConcertResponse>> getAllConcerts(){
        // concert 리스트들을 리턴한다.
        List<ConcertResponse> concertList = List.of(
                new ConcertResponse(0L, "나훈아50주년콘서트"),
                new ConcertResponse(1L, "아일릿데뷔콘서트"),
                new ConcertResponse(2L, "아이유드림콘서트")
        );

//        List<Concert> concertList = concertService.getAllConcerts();
        return ResponseEntity.ok().body(concertList);
    }

    @GetMapping("/concerts/{concert_id}/concert-times")
    public ResponseEntity<List<ConcertTimeResponse>> getAvailableConcertTime(@PathVariable(value="concert_id") long concertId){

        List<ConcertTimeResponse> concertTimeResponseList = List.of(
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
//        List<ConcertTime> concertTimes = concertService.getAllTimes(concertId);
//
        return ResponseEntity.ok().body(concertTimeResponseList);

    }

    @GetMapping("/concerts/{concert_id}/concert-times/{concert_time}/seats")
    public ResponseEntity<List<SeatResponse>> getAvailableSeat(
            @PathVariable(value="concert_id") long concertId,
            @PathVariable(value="concert_time") long concertTimeId){

        List<SeatResponse> seatList = List.of(
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

        return ResponseEntity.ok().body(seatList);


//        List<Seat> seat = concertService.getAllSeats(concertId, concertTimeId);
//
//        return ResponseEntity.ok().body(seatResponse);


    }
}
