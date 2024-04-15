package com.project1.ticketing.api.dto.response;

import com.project1.ticketing.domain.payment.models.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse {

    private long userId;
    private Payment payment;

    public PaymentResponse(long userId, Payment payment) {
        this.userId = userId;
        this.payment = payment;
    }
}
