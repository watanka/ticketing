package com.project1.ticketing.domain.payment.components;

import com.project1.ticketing.api.dto.request.PaymentRequestDTO;
import com.project1.ticketing.api.dto.response.PaymentResponseDTO;

public interface IPaymentService {
    PaymentResponseDTO pay(PaymentRequestDTO paymentRequestDTO);
    PaymentResponseDTO checkPayment(long userId, long reservationId);
}
