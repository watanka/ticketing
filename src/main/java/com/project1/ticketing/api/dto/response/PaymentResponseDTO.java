package com.project1.ticketing.api.dto.response;

import com.project1.ticketing.domain.payment.models.PaymentStatus;
import com.project1.ticketing.domain.reservation.models.Reservation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDTO {

    private long userId;
    private Reservation reservation;
    private String token;
    private String concertDate;
    private long seatId;
    private PaymentStatus paymentStatus;

    public PaymentResponseDTO(long userId, String token, String concertDate, long seatId, PaymentStatus paymentStatus) {
        this.userId = userId;
        this.token = token;
        this.concertDate = concertDate;
        this.seatId = seatId;
        this.paymentStatus = paymentStatus;
    }
}
