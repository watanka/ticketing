package com.project1.ticketing.api.controller;

import com.project1.ticketing.api.dto.response.ConcertResponse;
import com.project1.ticketing.api.usecase.ConcertUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConcertController {

    ConcertUseCase concertUseCase;

    @Autowired
    public ConcertController(ConcertUseCase concertUseCase) {
        this.concertUseCase = concertUseCase;
    }

    @GetMapping("/concerts")
    public ConcertResponse getConcertList(){
        return concertUseCase.getConcertList();
    }

    @GetMapping("/concerts/{concert_id}/concert-times")
    public ResponseEntity<ConcertResponse> getAvailableConcertTime(@PathVariable(value="concert_id") long concertId){

        ConcertResponse concertResponse = concertUseCase.getAvailableConcertTimeList(concertId);

        return ResponseEntity.ok().body(concertResponse);

    }

    @GetMapping("/concerts/{concert_id}/concert-times/{concert_time}/seats")
    public ResponseEntity<ConcertResponse> getAvailableSeat(
            @PathVariable(value="concert_id") long concertId,
            @PathVariable(value="concert_time") long concertTimeId){

        ConcertResponse concertResponse = concertUseCase.getAvailableSeatList(concertId, concertTimeId);

        return ResponseEntity.ok().body(concertResponse);

    }
}
