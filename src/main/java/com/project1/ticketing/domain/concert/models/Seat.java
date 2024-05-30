package com.project1.ticketing.domain.concert.models;

import com.project1.ticketing.api.dto.response.SeatResponse;
import com.project1.ticketing.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Seat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    long seatNum;

    @Column(name="concert_time_id")
    private long concertTimeId;

    long price;

    @Enumerated(EnumType.STRING)
    SeatStatus status;

    @Version
    private int version;



    // test용도
    public Seat(long seatNum, long concertTimeId, long price){
        this.seatNum = seatNum;
        this.concertTimeId = concertTimeId;
        status = SeatStatus.AVAILABLE;
    }


    public static Seat from(SeatResponse seatResponse){
        return Seat.builder()
                .id(seatResponse.getId())
                .price(seatResponse.getPrice())
                .status(SeatStatus.fromBool(seatResponse.isAvailable()))
                .build();
    }

    public void changeStatus(SeatStatus status){
        this.status = status;
    }
}
