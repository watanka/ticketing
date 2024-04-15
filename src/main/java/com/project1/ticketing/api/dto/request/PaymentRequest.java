package com.project1.ticketing.api.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private long userId;
    private long reservationId;

    public PaymentRequest(long userId, long reservationId) {
        this.userId = userId;
        this.reservationId = reservationId;
    }
}
