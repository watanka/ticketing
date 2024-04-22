package com.project1.ticketing.api.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationRequest {

    // token, user_id, concert_time, seat_num
    private long userId;
    private long concertTimeId;
    private long seatId;


    public ReservationRequest(long userId, long concertTimeId, long seatId) {
        this.userId = userId;
        this.concertTimeId = concertTimeId;
        this.seatId = seatId;
    }

//    public static ZonedDateTime toZonedDateTime(String datetimeString) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd.HH-mm-ss");
//        return ZonedDateTime.of(LocalDateTime.parse(datetimeString, formatter));
//    }

}
