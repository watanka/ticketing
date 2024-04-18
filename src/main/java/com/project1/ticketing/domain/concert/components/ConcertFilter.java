package com.project1.ticketing.domain.concert.components;

import com.project1.ticketing.api.dto.response.ConcertTimeResponse;
import com.project1.ticketing.api.dto.response.SeatResponse;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.models.SeatStatus;
import com.project1.ticketing.domain.concert.repository.ISeatRepository;

import java.util.ArrayList;
import java.util.List;

public class ConcertFilter {

    ISeatRepository seatRepository;

    public List<ConcertTimeResponse> filterTime(List<ConcertTime> concertTimeList){
        List<ConcertTimeResponse> availableTimeList = new ArrayList<>();
        for (ConcertTime concertTime : concertTimeList) {
            if (isTimeAvailable(concertTime.getId())){
                availableTimeList.add(ConcertTimeResponse.from(concertTime));
            }
        }

        return availableTimeList;
    }

    public List<SeatResponse> filterSeat(List<Seat> seatList){
        List<SeatResponse> availableSeatList = new ArrayList<>();
        for (Seat seat : seatList) {
            if (seat.getStatus() == SeatStatus.AVAILABLE){
                availableSeatList.add(SeatResponse.from(seat));
            }
        }
        return availableSeatList;
    }


    public boolean isTimeAvailable(long concertTimeId){
        return seatRepository.getAvailableSeatsbyConcertTimeId(concertTimeId).isPresent();
    }

}
