package com.project1.ticketing.api.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDTO {
    private long userId;
    private String token;
    private String concertTime;
    private long seatId;

    public PaymentRequestDTO(long userId, String token, String concertTime, long seatId) {
        this.userId = userId;
        this.token = token;
        this.concertTime = concertTime;
        this.seatId = seatId;
    }
}
