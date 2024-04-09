package com.project1.ticketing.api.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDTO {
    private long userId;
    private long reservationId;

    public PaymentRequestDTO(long userId, long reservationId) {
        this.userId = userId;
        this.reservationId = reservationId;
    }
}
