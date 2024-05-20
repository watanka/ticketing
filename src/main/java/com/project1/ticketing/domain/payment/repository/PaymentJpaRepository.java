package com.project1.ticketing.domain.payment.repository;

import com.project1.ticketing.domain.payment.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {

    Payment save(Payment payment);

    Payment findByReservationId(long reservationId);

    Payment findById(long paymentId);
}
