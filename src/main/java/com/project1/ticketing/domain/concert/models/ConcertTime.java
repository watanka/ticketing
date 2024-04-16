package com.project1.ticketing.domain.concert.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Builder
@Getter
public class ConcertTime{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private ZonedDateTime time;

    @ManyToOne
    @JoinColumn(name="concert_id")
    private Concert concert;

    @ManyToOne
    @JoinColumn(name="concerthall_id")
    ConcertHall concertHall;
    int maxSeatNum;
    int currAvailableSeatNum;

    public static ZonedDateTime fromStr(String timeStr){
        /// str format: "yyyy/MM/dd/ HH:mm:ss z" e.g) "2024/04/13/ 20:30:00 KST"
        return ZonedDateTime.parse(timeStr, DateTimeFormatter.ofPattern("yyyy/MM/dd/ HH:mm:ss z"));
    }

}


