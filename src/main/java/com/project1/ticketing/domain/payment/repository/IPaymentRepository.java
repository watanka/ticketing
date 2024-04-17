package com.project1.ticketing.domain.payment.repository;

import com.project1.ticketing.domain.payment.models.Payment;
import com.project1.ticketing.domain.reservation.models.Reservation;

import java.util.Optional;

public interface IPaymentRepository {
    void save(Payment payment);
    Optional<Payment> findByReservation(Reservation reservation);

    Optional<Payment> findById(long paymentId);
}
