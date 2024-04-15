package com.project1.ticketing.domain.concert.models;

import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
public class ConcertTime{

    long id;
    ZonedDateTime time;
    long concertId;
    long concertHallId;
    int maxSeatNum;
    int currAvailableSeatNum;

    public static ZonedDateTime fromStr(String timeStr){
        /// str format: "yyyy/MM/dd/ HH:mm:ss z" e.g) "2024/04/13/ 20:30:00 KST"
        return ZonedDateTime.parse(timeStr, DateTimeFormatter.ofPattern("yyyy/MM/dd/ HH:mm:ss z"));
    }

}


