package com.project1.ticketing.api.dto.response;

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
