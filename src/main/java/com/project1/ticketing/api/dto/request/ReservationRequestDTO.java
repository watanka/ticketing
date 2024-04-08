package com.project1.ticketing.api.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationRequestDTO {

    // token, user_id, concert_time, seat_num
    private String token;
    private long userId;
    private String concertTime;
    private long seatId;


    public ReservationRequestDTO(String token, long userId, String concertTime, long seatId) {
        this.token = token;
        this.userId = userId;
        this.concertTime = concertTime;
        this.seatId = seatId;
    }
}
