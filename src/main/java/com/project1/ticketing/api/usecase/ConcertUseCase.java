package com.project1.ticketing.api.usecase;

import com.project1.ticketing.api.dto.response.ConcertResponse;

import com.project1.ticketing.domain.concert.components.ConcertService;
import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ConcertUseCase {
    ConcertService concertService;

//    @Autowired
//    public ConcertUseCase(ConcertService concertService) {
//        this.concertService = concertService;
//    }

}