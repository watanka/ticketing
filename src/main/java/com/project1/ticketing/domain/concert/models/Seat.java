package com.project1.ticketing.domain.concert.models;

import com.project1.ticketing.api.dto.response.SeatResponse;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Seat{

    @Id
    @GeneratedValue
    long id;
    long seatNum;

    @Column(name="concerttime_id")
    private long concertTimeId;

    @Column(name="concerthall_id")
    long concertHallId;

    long price;

    @Enumerated(EnumType.STRING)
    SeatStatus status;


    // test용도
    public Seat(long seatNum, long concertTimeId, long price){
        this.seatNum = seatNum;
        this.concertTimeId = concertTimeId;
        status = SeatStatus.AVAILABLE;
    }

    public static Seat from(SeatResponse seatResponse){
        return Seat.builder()
                .id(seatResponse.getId())
                .concertHallId(seatResponse.getConcertHallId())
                .price(seatResponse.getPrice())
                .status(SeatStatus.fromBool(seatResponse.isAvailable()))
                .build();
    }

    public void changeStatus(SeatStatus status){
        this.status = status;
    }
}
