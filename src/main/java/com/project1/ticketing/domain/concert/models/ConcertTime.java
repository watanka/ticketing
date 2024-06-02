package com.project1.ticketing.domain.concert.models;

import com.project1.ticketing.api.dto.response.ConcertTimeResponse;
import com.project1.ticketing.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="concert_time")
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ConcertTime extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="concert_time_id")
    long id;

    private ZonedDateTime concertTime;

    @Column(name="concert_id")
    private long concertId;

//    @OneToMany(mappedBy="concertTimeId", cascade = CascadeType.ALL)
//    private List<Seat> seats = new ArrayList<>();

    //Test 용도
    public ConcertTime(long id, ZonedDateTime concertTime){
        this.id = id;
        this.concertTime = concertTime;
    }


    public static ZonedDateTime fromStr(String timeStr){
        /// str format: "yyyy/MM/dd/ HH:mm:ss z" e.g) "2024/04/13/ 20:30:00 KST"
        return ZonedDateTime.parse(timeStr, DateTimeFormatter.ofPattern("yyyy/MM/dd/ HH:mm:ss z"));
    }

    public static ConcertTime from(ConcertTimeResponse concertTimeResponse){
        return ConcertTime.builder()
                .id(concertTimeResponse.getId())
                .concertTime(concertTimeResponse.getTime())
//                .concertHallId(concertTimeResponse.getConcertHallId())
//                .maxSeatNum(concertTimeResponse.getMaxSeatNum())
//                .currAvailableSeatNum(concertTimeResponse.getCurrAvailableSeatNum())
                .build();
    }

}


