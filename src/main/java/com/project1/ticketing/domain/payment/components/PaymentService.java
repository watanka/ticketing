package com.project1.ticketing.domain.payment.components;

import com.project1.ticketing.api.dto.request.PaymentRequest;
import com.project1.ticketing.api.dto.response.PaymentResponse;
import com.project1.ticketing.domain.payment.models.Payment;
import com.project1.ticketing.domain.payment.models.PaymentStatus;
import com.project1.ticketing.domain.payment.repository.IPaymentRepository;
import com.project1.ticketing.domain.reservation.models.Reservation;
import com.project1.ticketing.domain.reservation.models.ReservationStatus;
import com.project1.ticketing.domain.reservation.repository.IReservationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService{
    IPaymentRepository paymentRepository;
    PaymentValidator paymentValidator;



    public Payment pay(long price, long balance) {

        Payment payment = new Payment(reservation);


        paymentValidator.validate(price, balance);

        payment.updateStatus(PaymentStatus.PAID);

        paymentRepository.save(payment);

        return payment;
    }

    public Optional<Payment> checkPayment(long paymentId) {
        return paymentRepository.findById(paymentId);
    }
}
