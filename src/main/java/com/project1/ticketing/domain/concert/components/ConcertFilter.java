package com.project1.ticketing.domain.concert.components;

import com.project1.ticketing.api.dto.response.ConcertTimeResponse;
import com.project1.ticketing.api.dto.response.SeatResponse;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.models.SeatStatus;
import com.project1.ticketing.domain.concert.repository.ConcertCoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConcertFilter {

    ConcertCoreRepository concertCoreRepository;

    @Autowired
    public ConcertFilter(ConcertCoreRepository concertCoreRepository) {
        this.concertCoreRepository = concertCoreRepository;
    }

    public List<ConcertTimeResponse> filterAvailableTime(List<ConcertTime> concertTimeList){
        List<ConcertTimeResponse> availableTimeList = new ArrayList<>();
        for (ConcertTime concertTime : concertTimeList) {
            if (isTimeAvailable(concertTime.getId())){
                availableTimeList.add(ConcertTimeResponse.from(concertTime));
            }
        }

        return availableTimeList;
    }

    public List<SeatResponse> filterAvailableSeat(List<Seat> seatList){
        List<SeatResponse> availableSeatList = new ArrayList<>();
        for (Seat seat : seatList) {
            if (seat.getStatus() == SeatStatus.AVAILABLE){
                availableSeatList.add(SeatResponse.from(seat));
            }
        }
        return availableSeatList;
    }


    public boolean isTimeAvailable(long concertTimeId){
        return concertCoreRepository.isconcertTimeAvailable(concertTimeId);
    }

}
