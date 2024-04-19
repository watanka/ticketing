package com.project1.ticketing.domain.concert.components;

import com.project1.ticketing.domain.concert.infrastructure.ConcertCoreRepositoryImpl;
import com.project1.ticketing.domain.concert.repository.ConcertCoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ConcertValidator {
    ConcertCoreRepository concertRepository;

    @Autowired
    public ConcertValidator(ConcertCoreRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public void validateConcert(long concertId){
        if (concertRepository.findConcertById(concertId).isEmpty()){
            throw new RuntimeException("콘서트 정보를 찾을 수 없습니다.");
        }
    }

    public void validateConcertTime(long concertTimeId){
        if (concertRepository.findConcertTimeById(concertTimeId).isEmpty()){
            throw new RuntimeException("콘서트 시간 정보를 찾을 수 없습니다.");
        }
    }

    public void validateSeat(long seatId){
        if (concertRepository.findSeatById(seatId).isEmpty()){
            throw new RuntimeException("좌석 정보를 찾을 수 없습니다.");
        }
    }
}
