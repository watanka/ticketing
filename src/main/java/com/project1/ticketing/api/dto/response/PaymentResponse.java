package com.project1.ticketing.api.dto.response;

import com.project1.ticketing.domain.payment.models.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse {

    private long userId;
    private long reservationId;
    private long seatId;
    private long price;

    public PaymentResponse(long userId, long reservationId, long seatId, long price) {
        this.userId = userId;
        this.reservationId = reservationId;
        this.seatId = seatId;
        this.price = price;
    }
}
