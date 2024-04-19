package com.project1.ticketing.domain.concert.components;

import com.project1.ticketing.api.dto.response.ConcertResponse;
import com.project1.ticketing.api.dto.response.ConcertTimeResponse;
import com.project1.ticketing.api.dto.response.SeatResponse;
import com.project1.ticketing.domain.concert.infrastructure.ConcertRepositoryImpl;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.repository.IConcertRepository;
import com.project1.ticketing.domain.concert.repository.IConcertTimeRepository;
import com.project1.ticketing.domain.concert.repository.ISeatRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ConcertService implements IConcertService{

    IConcertRepository concertRepository;

    ConcertValidator concertValidator;
    ConcertFilter concertFilter;

    public ConcertService(ConcertRepositoryImpl concertRepository, ConcertValidator concertValidator, ConcertFilter concertFilter) {

//        IConcertRepository concertRepository, IConcertTimeRepository concertTimeRepository, ISeatRepository seatRepository,
//        this.concertRepository = concertRepository;
//        this.concertTimeRepository = concertTimeRepository;
//        this.seatRepository = seatRepository;
        this.concertRepository = concertRepository;
        this.concertValidator = concertValidator;
        this.concertFilter = concertFilter;
    }

    @Override
    public List<ConcertResponse> getAllConcerts() {

        return concertRepository.findAll().stream()
                .map(ConcertResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConcertTimeResponse> getAvailableTimes(long concertId) {
        concertValidator.validateConcert(concertId);
        List<ConcertTime> concertTimeList = concertRepository.findAllByConcertId(concertId);

        return concertFilter.filterAvailableTime(concertTimeList);

    }

    @Override
    public List<SeatResponse> getAvailableSeats(long concertTimeId) {
        concertValidator.validateConcertTime(concertTimeId);
        List<Seat> seatList = concertRepository.findByConcertTimeId(concertTimeId);

        return concertFilter.filterAvailableSeat(seatList);



    }





    public boolean isSeatReserved(long seatId){
        concertValidator.validateSeat(seatId);
        return concertRepository.isAvailable(seatId);
    }

}
