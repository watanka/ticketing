package com.project1.ticketing.api.controller;

import com.project1.ticketing.domain.concert.components.ConcertService;
import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
public class ConcertController {
//
//    ConcertService concertService;
//
//    public ConcertController(ConcertService concertService) {
//        this.concertService = concertService;
//    }
//
//    @GetMapping("concerts/")
//    List<ConcertResponseDTO> listConcert(){
//        return concertService.getConcert();
//    }
//
//    @PostMapping("concerts/{concert_id}")
//    List<ConcertTime> listConcertTime(@PathVariable(value = "concert_id") long concertId){
//        return concertService.getConcertDate(concertId);
//    }
//
//    @GetMapping("concerts/{concert_id}")
//    List<Seat> listAllSeatInConcertDate(
//            @PathVariable(value="concert_id") long concertId,
//            @RequestParam("concert_time") long concertTimeId){
//        return concertService.getAllSeat(concertId, concertTimeId);
//    }
//

}
