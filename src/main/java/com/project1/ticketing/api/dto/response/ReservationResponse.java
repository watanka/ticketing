package com.project1.ticketing.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project1.ticketing.domain.reservation.models.Reservation;
import com.project1.ticketing.domain.reservation.models.ReservationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;


@Builder
public record ReservationResponse (long id,
                                   long userId,
                                   long seatNum,
                                   long price,
                                   ReservationStatus status,
                                   long concertTimeId,
                                   ZonedDateTime expiredAt

) {



    public ReservationResponse(long id, long userId, long seatNum, long price, ReservationStatus status, long concertTimeId, ZonedDateTime expiredAt) {
        this.id = id;
        this.userId = userId;
        this.seatNum = seatNum;
        this.price = price;
        this.status = status;
        this.concertTimeId = concertTimeId;
        this.expiredAt = expiredAt;
    }

    public static ReservationResponse from(Reservation reservation){
        return ReservationResponse.builder()
                .id(reservation.getId())
                .userId(reservation.getUserId())
                .seatNum(reservation.getSeatNum())
                .price(reservation.getPrice())
                .status(reservation.getStatus())
                .concertTimeId(reservation.getConcertTimeId())
                .expiredAt(reservation.getCreatedAt().plusMinutes(5))
                .build();
    }

}
