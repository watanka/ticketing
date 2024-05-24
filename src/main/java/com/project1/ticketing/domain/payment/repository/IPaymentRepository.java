package com.project1.ticketing.domain.payment.repository;

import com.project1.ticketing.domain.payment.models.Payment;
import com.project1.ticketing.domain.reservation.models.Reservation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPaymentRepository {
    void save(Payment payment);
    Payment findByReservationId(long reservationId);

    Payment findById(long paymentId);
}
