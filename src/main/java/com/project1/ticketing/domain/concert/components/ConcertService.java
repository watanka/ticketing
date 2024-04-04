package com.project1.ticketing.domain.concert.components;

import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.repository.ConcertRepository;
import com.project1.ticketing.domain.concert.repository.ConcertTimeRepository;
import com.project1.ticketing.domain.concert.repository.SeatRepository;

import java.util.List;
import java.util.Optional;

public class ConcertService { // 우선 서비스로 정의하고, 필요하면 기능 나누기

    ConcertRepository concertRepository;
    ConcertTimeRepository concertTimeRepository;
    SeatRepository seatRepository;

    public ConcertService(ConcertRepository concertRepository, ConcertTimeRepository concertTimeRepository, SeatRepository seatRepository) {
        this.concertRepository = concertRepository;
        this.concertTimeRepository = concertTimeRepository;
        this.seatRepository = seatRepository;
    }

    // 콘서트 예약 가능 날짜 조회

    private List<Concert> getConcert(){
        return concertRepository.getAll();
    }
    private List<ConcertTime> getConcertDate(long concertId){
        return concertTimeRepository.getAllByConcertId(concertId);
    }


    // 콘서트 예약 가능 좌석 조회
    private Optional<Seat> getAllSeat(long concertId, long concertDateId){
        return seatRepository.getAllByConcertAndConcertDate(concertId, concertDateId);
    }

}
