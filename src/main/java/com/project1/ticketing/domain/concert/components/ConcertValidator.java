package com.project1.ticketing.domain.concert.components;

import com.project1.ticketing.domain.concert.repository.IConcertRepository;
import com.project1.ticketing.domain.concert.repository.IConcertTimeRepository;
import com.project1.ticketing.domain.concert.repository.ISeatRepository;

public class ConcertValidator {
    IConcertRepository concertRepository;
    IConcertTimeRepository concertTimeRepository;
    ISeatRepository seatRepository;

    public ConcertValidator(IConcertRepository concertRepository, IConcertTimeRepository concertTimeRepository, ISeatRepository seatRepository) {
        this.concertRepository = concertRepository;
        this.concertTimeRepository = concertTimeRepository;
        this.seatRepository = seatRepository;
    }

    public void validateConcert(long concertId){
        if (concertRepository.findById(concertId).isEmpty()){
            throw new RuntimeException("콘서트 정보를 찾을 수 없습니다.");
        }
    }

    public void validateConcertTime(long concertTimeId){
        if (concertTimeRepository.findById(concertTimeId).isEmpty()){
            throw new RuntimeException("콘서트 시간 정보를 찾을 수 없습니다.");
        }
    }

    public void validateSeat(long seatId){
        if (seatRepository.findById(seatId).isEmpty()){
            throw new RuntimeException("좌석 정보를 찾을 수 없습니다.");
        }
    }
}
