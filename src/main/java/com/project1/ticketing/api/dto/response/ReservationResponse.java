package com.project1.ticketing.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project1.ticketing.domain.reservation.models.Reservation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReservationResponse {

    private long id;
    private long userId;
    private long seatNum;
    private long price;
    private String status;
    private String concertTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String expiredAt;


    public ReservationResponse(long id, long userId, long seatNum, long price, String status, String concertTime, String expiredAt) {
        this.id = id;
        this.userId = userId;
        this.seatNum = seatNum;
        this.price = price;
        this.status = status;
        this.concertTime = concertTime;
        this.expiredAt = expiredAt;
    }

    public static ReservationResponse from(Reservation reservation){
        return ReservationResponse.builder()
                .id(reservation.getId())
                .userId(reservation.getUserId())
                .seatNum(reservation.getSeatNum())
                .price(reservation.getPrice())
                .status(reservation.getStatus().toString())
                .concertTime(reservation.getConcertTime())
                .expiredAt(reservation.getCreateAt().plusMinutes(5).toString())
                .build();
    }

}
