package com.project1.ticketing.api.dto.response;

import com.project1.ticketing.domain.payment.models.Payment;
import com.project1.ticketing.domain.payment.models.PaymentStatus;
import com.project1.ticketing.domain.reservation.models.Reservation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDTO {

    private long userId;
    private Payment payment;

    public PaymentResponseDTO(long userId, Payment payment) {
        this.userId = userId;
        this.payment = payment;
    }
}
