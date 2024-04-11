package com.project1.ticketing.api.controller;

import com.project1.ticketing.api.dto.response.ConcertResponseDTO;
import com.project1.ticketing.api.usecase.ConcertUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConcertController {

    ConcertUseCase concertUseCase;

    public ConcertController(ConcertUseCase concertUseCase) {
        this.concertUseCase = concertUseCase;
    }

    @GetMapping("/concerts")
    public ConcertResponseDTO getConcertList(){
        return concertUseCase.getConcertList();
    }

    @GetMapping("/concerts/{concert_id}/concert-times")
    public ResponseEntity<ConcertResponseDTO> getAvailableConcertTime(@PathVariable(value="concert_id") long concertId){

        ConcertResponseDTO concertResponseDTO = concertUseCase.getAvailableConcertTimeList(concertId);

        return ResponseEntity.ok().body(concertResponseDTO);

    }

    @GetMapping("/concerts/{concert_id}/concert-times/{concert_time}/seats")
    public ResponseEntity<ConcertResponseDTO> getAvailableSeat(
            @PathVariable(value="concert_id") long concertId,
            @PathVariable(value="concert_time") String concertTime){

        ConcertResponseDTO concertResponseDTO = concertUseCase.getAvailableSeatList(concertId, concertTime);

        return ResponseEntity.ok().body(concertResponseDTO);

    }
}
