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

    SeatStatus status;




    public static Seat from(SeatResponse seatResponse){
        return Seat.builder()
                .id(seatResponse.getId())
                .concertHallId(seatResponse.getConcertHallId())
                .price(seatResponse.getPrice())
                .status(SeatStatus.fromBool(seatResponse.isAvailable()))
                .build();
    }
}
