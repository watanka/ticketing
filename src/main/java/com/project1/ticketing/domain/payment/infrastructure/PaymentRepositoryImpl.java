package com.project1.ticketing.domain.payment.infrastructure;

import com.project1.ticketing.domain.payment.models.Payment;
import com.project1.ticketing.domain.payment.repository.IPaymentRepository;
import com.project1.ticketing.domain.payment.repository.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements IPaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public void save(Payment payment) {
        paymentJpaRepository.save(payment);
    }

    @Override
    public Payment findByReservationId(long reservationId) {
        return paymentJpaRepository.findByReservationId(reservationId);
    }

    @Override
    public Payment findById(long paymentId) {
        return paymentJpaRepository.findById(paymentId);
    }
}
