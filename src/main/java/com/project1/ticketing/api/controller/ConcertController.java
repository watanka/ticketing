package com.project1.ticketing.api.controller;

import com.project1.ticketing.api.controller.mock.FakeConcertService;
import com.project1.ticketing.api.dto.response.ConcertResponse;
import com.project1.ticketing.api.dto.response.ConcertTimeResponse;
import com.project1.ticketing.api.dto.response.SeatResponse;
import com.project1.ticketing.domain.concert.components.ConcertService;
import com.project1.ticketing.domain.concert.components.IConcertService;
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

    IConcertService concertService;
    @Autowired
    public ConcertController(FakeConcertService concertService) {
        this.concertService = concertService;
    }

    @GetMapping("/concerts")
    public ResponseEntity<List<ConcertResponse>> getAllConcerts(){
        List<ConcertResponse> concertList = concertService.getAllConcerts();

        return ResponseEntity.ok().body(concertList);
    }

    @GetMapping("/concerts/{concert_id}/concert-times")
    public ResponseEntity<List<ConcertTimeResponse>> getAvailableTimes(@PathVariable(value="concert_id") long concertId){
        List<ConcertTimeResponse> concertTimeResponseList = concertService.getAvailableTimes(concertId);

        return ResponseEntity.ok().body(concertTimeResponseList);

    }

    @GetMapping("/concerts/{concert_id}/concert-times/{concert_time}/seats")
    public ResponseEntity<List<SeatResponse>> getAvailableSeats(
            @PathVariable(value="concert_id") long concertId,
            @PathVariable(value="concert_time") long concertTimeId){

        List<SeatResponse> seatList = concertService.getAvailableSeats(concertTimeId);

        return ResponseEntity.ok().body(seatList);

    }
}
