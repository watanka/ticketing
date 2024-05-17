package com.project1.ticketing.domain.payment.components;

import com.project1.ticketing.api.dto.request.PaymentRequest;
import com.project1.ticketing.api.dto.response.PaymentResponse;
import com.project1.ticketing.domain.payment.models.Payment;
import com.project1.ticketing.domain.payment.models.PaymentStatus;
import com.project1.ticketing.domain.payment.repository.IPaymentRepository;
import com.project1.ticketing.domain.reservation.models.Reservation;
import com.project1.ticketing.domain.reservation.models.ReservationStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;


public class PaymentService{
    IPaymentRepository paymentRepository;
    PaymentValidator paymentValidator;

    public PaymentService(IPaymentRepository paymentRepository, PaymentValidator paymentValidator) {
        this.paymentRepository = paymentRepository;
        this.paymentValidator = paymentValidator;
    }

    public Payment pay(Reservation reservation, long balance) {

        Payment payment = new Payment(reservation);

        long price = 3000;//reservation.getSeatId().getPrice();

        paymentValidator.validate(price, balance);

        payment.updateStatus(PaymentStatus.PAID);

        paymentRepository.save(payment);

        return payment;
    }

    public Payment cancel(Reservation reservation){
        return null;
    }

    public Optional<Payment> checkPayment(long paymentId) {
        return paymentRepository.findById(paymentId);
    }
}
