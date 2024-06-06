package com.project1.ticketing.api.dto.request;

import com.project1.ticketing.domain.reservation.models.Reservation;

public record ReservationRequest(long userId, long concertId, long concertTimeId, long seatNum){

    public Reservation toEntity(){
        return Reservation.builder()
                .concertTimeId(concertTimeId)
                .seatNum(seatNum)
                .userId(userId)
                .build();
    }

}