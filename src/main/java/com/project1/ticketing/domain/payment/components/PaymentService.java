package com.project1.ticketing.domain.payment.components;

import com.project1.ticketing.api.dto.request.PaymentRequest;
import com.project1.ticketing.api.dto.response.PaymentResponse;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService{
    @Override
    public PaymentResponse pay(PaymentRequest paymentRequest) {
        return null;
    }

    @Override
    public PaymentResponse checkPayment(long userId, long reservationId) {
        return null;
    }
}
