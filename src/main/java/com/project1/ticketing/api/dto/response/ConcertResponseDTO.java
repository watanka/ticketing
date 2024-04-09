package com.project1.ticketing.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConcertResponseDTO{
    private List<String> concertNameList;
    private long concertId;
    private String concertTime;
    private List<String> concertTimeList;
    private long seat;
    private List<Integer> seatList;  // 한 번에 한 좌석만


    public ConcertResponseDTO(List<String> concertNameList){
        this.concertNameList = concertNameList;
    }
    public ConcertResponseDTO(long concertId) {
        this.concertId = concertId;
    }

    public ConcertResponseDTO(long concertId, List<String> concertTimeList) {
        this.concertId = concertId;
        this.concertTimeList = concertTimeList;
    }


    public ConcertResponseDTO(long concertId, String concertTime, List<Integer> seatList) {
        this.concertId = concertId;
        this.concertTime = concertTime;
        this.seatList = seatList;
    }
}
