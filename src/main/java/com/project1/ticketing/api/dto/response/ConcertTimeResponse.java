package com.project1.ticketing.api.dto.response;

import com.project1.ticketing.domain.concert.models.ConcertTime;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class ConcertTimeResponse {
    long id;
    ZonedDateTime time;
//    long concertHallId;
//    int maxSeatNum;
//    int currAvailableSeatNum;

    public static ConcertTimeResponse from(ConcertTime concertTime){
        return ConcertTimeResponse.builder()
                .id(concertTime.getId())
                .time(concertTime.getConcertTime())
//                .concertHallId(concertTime.getConcertHallId())
//                .maxSeatNum(concertTime.getMaxSeatNum())
//                .currAvailableSeatNum(concertTime.getCurrAvailableSeatNum())
                .build();
    }

    public static ZonedDateTime fromStr(String timeStr){
        /// str format: "yyyy/MM/dd/ HH:mm:ss z" e.g) "2024/04/13/ 20:30:00 KST"
        return ZonedDateTime.parse(timeStr, DateTimeFormatter.ofPattern("yyyy/MM/dd/ HH:mm:ss z"));
    }

}
