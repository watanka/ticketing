package com.project1.ticketing.api.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationRequest {

    // token, user_id, concert_time, seat_num
    private String token;
    private long userId;
    private String concertTime;
    private long seatId;


    public ReservationRequest(String token, long userId, String concertTime, long seatId) {
        this.token = token;
        this.userId = userId;
        this.concertTime = concertTime;
        this.seatId = seatId;
    }

//    public static ZonedDateTime toZonedDateTime(String datetimeString) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd.HH-mm-ss");
//        return ZonedDateTime.of(LocalDateTime.parse(datetimeString, formatter));
//    }

}