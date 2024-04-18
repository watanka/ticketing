package com.project1.ticketing.domain.concert.models;

import com.project1.ticketing.api.dto.response.SeatResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

//@Entity
@Getter
public class Seat{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    long seatNum;

//    @ManyToOne
//    @JoinColumn(name = "concertTime_id")
    private ConcertTime concertTime;


    long concertHallId;

    long price;

    SeatStatus status;


    @Builder
    public Seat(long id, long concertHallId, long price, SeatStatus status) {
        this.id = id;
        this.concertHallId = concertHallId;
        this.price = price;
        this.status = status;
    }

    public static Seat from(SeatResponse seatResponse){
        return Seat.builder()
                .id(seatResponse.getId())
                .concertHallId(seatResponse.getConcertHallId())
                .price(seatResponse.getPrice())
                .status(SeatStatus.fromBool(seatResponse.isAvailable()))
                .build();
    }
}
