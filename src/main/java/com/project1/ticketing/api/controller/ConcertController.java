package com.project1.ticketing.api.controller;

import com.project1.ticketing.api.dto.response.ConcertResponse;
import com.project1.ticketing.api.dto.response.ConcertTimeResponse;
import com.project1.ticketing.api.dto.response.SeatResponse;
import com.project1.ticketing.domain.concert.components.ConcertService;
import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConcertController {

    ConcertService concertService;

    @Autowired
    public ConcertController(ConcertService concertService) {
        this.concertService = concertService;
    }

    @GetMapping("/concerts")
    public ConcertResponse getAllConcerts(){

//        List<Concert> concertList = concertService.getAllConcerts();

        return null;
    }

    @GetMapping("/concerts/{concert_id}/concert-times")
    public ResponseEntity<ConcertTimeResponse> getAvailableConcertTime(@PathVariable(value="concert_id") long concertId){

//        List<ConcertTime> concertTimes = concertService.getAllTimes(concertId);
//
//        return ResponseEntity.ok().body(concertTimeResponse);
        return null;
    }

    @GetMapping("/concerts/{concert_id}/concert-times/{concert_time}/seats")
    public ResponseEntity<SeatResponse> getAvailableSeat(
            @PathVariable(value="concert_id") long concertId,
            @PathVariable(value="concert_time") long concertTimeId){

//        List<Seat> seat = concertService.getAllSeats(concertId, concertTimeId);
//
//        return ResponseEntity.ok().body(seatResponse);
        return null;

    }
}
