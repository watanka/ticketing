package com.project1.ticketing.domain.concert.components;

import com.project1.ticketing.api.dto.response.ConcertResponse;
import com.project1.ticketing.api.dto.response.ConcertTimeResponse;
import com.project1.ticketing.api.dto.response.SeatResponse;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.repository.IConcertHallRepository;
import com.project1.ticketing.domain.concert.repository.IConcertRepository;
import com.project1.ticketing.domain.concert.repository.IConcertTimeRepository;
import com.project1.ticketing.domain.concert.repository.ISeatRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ConcertService implements IConcertService{

    IConcertRepository concertRepository;
    IConcertTimeRepository concertTimeRepository;
    ISeatRepository seatRepository;

    ConcertValidator concertValidator;
    ConcertFilter concertFilter;

    public ConcertService(IConcertRepository concertRepository, IConcertTimeRepository concertTimeRepository, ISeatRepository seatRepository, ConcertValidator concertValidator, ConcertFilter concertFilter) {
        this.concertRepository = concertRepository;
        this.concertTimeRepository = concertTimeRepository;
        this.seatRepository = seatRepository;
        this.concertValidator = concertValidator;
        this.concertFilter = concertFilter;
    }

    @Override
    public List<ConcertResponse> getAllConcerts() {

        return concertRepository.getAll().stream()
                .map(ConcertResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConcertTimeResponse> getAvailableTimes(long concertId) {
        concertValidator.validateConcert(concertId);
        List<ConcertTime> concertTimeList = concertTimeRepository.getAllByConcertId(concertId);

        return concertFilter.filterAvailableTime(concertTimeList);

    }

    @Override
    public List<SeatResponse> getAvailableSeats(long concertTimeId) {
        concertValidator.validateConcertTime(concertTimeId);
        List<Seat> seatList = seatRepository.getByConcertTimeId(concertTimeId);

        return concertFilter.filterAvailableSeat(seatList);



    }


    // 콘서트, 시간, 좌석 순 꽉 찼는지 확인
    public boolean isConcertFull(long concertId){
        concertValidator.validateConcert(concertId);
        return concertRepository.isFull(concertId);
    }



    public boolean isSeatReserved(long seatId){
        concertValidator.validateSeat(seatId);
        return seatRepository.isAvailable(seatId);
    }

}
