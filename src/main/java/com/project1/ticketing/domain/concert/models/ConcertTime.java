package com.project1.ticketing.domain.concert.models;

import com.project1.ticketing.api.dto.response.ConcertTimeResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//@Entity
@Builder
@Getter
public class ConcertTime{

//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="concertTime_id")
    long id;

    private ZonedDateTime time;

//    @ManyToOne
//    @JoinColumn(name="concert_id")
    private Concert concert;

    private List<Seat> seats = new ArrayList<>();

    long concertHallId;
    int maxSeatNum;
    int currAvailableSeatNum; // seat정보에 따라 업데이트

    public static ZonedDateTime fromStr(String timeStr){
        /// str format: "yyyy/MM/dd/ HH:mm:ss z" e.g) "2024/04/13/ 20:30:00 KST"
        return ZonedDateTime.parse(timeStr, DateTimeFormatter.ofPattern("yyyy/MM/dd/ HH:mm:ss z"));
    }

    public static ConcertTime from(ConcertTimeResponse concertTimeResponse){
        return ConcertTime.builder()
                .id(concertTimeResponse.getId())
                .time(concertTimeResponse.getTime())
                .concertHallId(concertTimeResponse.getConcertHallId())
                .maxSeatNum(concertTimeResponse.getMaxSeatNum())
                .currAvailableSeatNum(concertTimeResponse.getCurrAvailableSeatNum())
                .build();
    }

}


