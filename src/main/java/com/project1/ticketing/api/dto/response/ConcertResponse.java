package com.project1.ticketing.api.dto.response;

import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConcertResponse {
    private List<Concert> concertList;
    private long concertId;
    private long concertTimeId;
    private List<ConcertTime> concertTimeList;
    private long seat;
    private List<Seat> seatList;  // 한 번에 한 좌석만


    public ConcertResponse(List<Concert> concertList){

        this.concertList = concertList;
    }
    public ConcertResponse(long concertId) {
        this.concertId = concertId;
    }

    public ConcertResponse(long concertId, List<ConcertTime> concertTimeList) {
        this.concertId = concertId;
        this.concertList = concertList;
    }


    public ConcertResponse(long concertId, long concertTimeId, List<Seat> seatList) {
        this.concertId = concertId;
        this.concertTimeId = concertTimeId;
        this.seatList = seatList;
    }
}
