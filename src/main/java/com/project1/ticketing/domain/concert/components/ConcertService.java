package com.project1.ticketing.domain.concert.components;

import com.project1.ticketing.api.utils.exceptions.BaseException;
import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.repository.IConcertHallRepository;
import com.project1.ticketing.domain.concert.repository.IConcertRepository;
import com.project1.ticketing.domain.concert.repository.IConcertTimeRepository;
import com.project1.ticketing.domain.concert.repository.ISeatRepository;

import java.util.List;
import java.util.Optional;

public class ConcertService{

    IConcertRepository concertRepository;
    IConcertTimeRepository concertTimeRepository;
    ISeatRepository seatRepository;
    IConcertHallRepository concertHallRepository;

    public ConcertService(IConcertRepository concertRepository,
                          IConcertTimeRepository concertTimeRepository,
                          ISeatRepository seatRepository,
                          IConcertHallRepository concertHallRepository
                          ) {
        this.concertRepository = concertRepository;
        this.concertTimeRepository = concertTimeRepository;
        this.seatRepository = seatRepository;
        this.concertHallRepository = concertHallRepository;
    }

    public List<Concert> getAllConcerts() {
        return concertRepository.getAll();
    }

    public List<ConcertTime> getAllTimes(long concertId) {
        return concertTimeRepository.getAllByConcertId(concertId);
    }

    public List<Seat> getAllSeats(long concertId, long concertTimeId) {
        Optional<ConcertTime> concertTime = concertTimeRepository.getByTime(concertId, concertTimeId);

        if (concertTime.isEmpty()){
            throw new RuntimeException("해당 콘서트 시간을 찾지 못 했습니다.");
        }

        long concertHallId = concertTime.get().getConcertHall().getId();

        return seatRepository.getAllSeats(concertHallId);
    }


    // 콘서트, 시간, 좌석 순 꽉 찼는지 확인
    public boolean isConcertFull(long concertId){
        return concertRepository.isFull(concertId);
    }

    public boolean isTimeFull(long concertId, long concertTimeId){
        return concertTimeRepository.isFull(concertId, concertTimeId);
    }

    public boolean isSeatReserved(long concertId, long concertTime, long seatId){
        return seatRepository.isAvailable(concertId, concertTime, seatId);
    }

}
