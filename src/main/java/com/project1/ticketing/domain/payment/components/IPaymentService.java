package com.project1.ticketing.domain.payment.components;

import com.project1.ticketing.api.dto.request.PaymentRequest;
import com.project1.ticketing.api.dto.response.PaymentResponse;

public interface IPaymentService {
    PaymentResponse pay(PaymentRequest paymentRequest);
    PaymentResponse checkPayment(long userId, long reservationId);
}
