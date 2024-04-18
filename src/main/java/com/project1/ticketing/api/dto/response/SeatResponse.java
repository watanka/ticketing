package com.project1.ticketing.api.dto.response;

import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.models.SeatStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SeatResponse {
    long id;
    long seatNum;
    long concertHallId;
    long concertTimeId;
    long price;
    boolean available;


    public static SeatResponse from(Seat seat){
        return SeatResponse.builder()
                .id(seat.getId())
                .seatNum(seat.getSeatNum())
                .concertHallId(seat.getConcertHallId())
                .price(seat.getPrice())
                .available(seat.getStatus().toBoolean())
                .build();
    }

}
