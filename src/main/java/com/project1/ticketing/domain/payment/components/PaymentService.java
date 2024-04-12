package com.project1.ticketing.domain.payment.components;

import com.project1.ticketing.api.dto.request.PaymentRequestDTO;
import com.project1.ticketing.api.dto.response.PaymentResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService{
    @Override
    public PaymentResponseDTO pay(PaymentRequestDTO paymentRequestDTO) {
        return null;
    }

    @Override
    public PaymentResponseDTO checkPayment(long userId, long reservationId) {
        return null;
    }
}
