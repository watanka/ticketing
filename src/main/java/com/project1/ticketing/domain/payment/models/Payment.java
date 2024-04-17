package com.project1.ticketing.domain.payment.models;

import com.project1.ticketing.domain.point.models.User;
import com.project1.ticketing.domain.reservation.models.Reservation;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Getter @Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    Reservation reservation;

    ZonedDateTime createAt;
    PaymentStatus paymentStatus;

    @Builder
    public Payment(Reservation reservation) {
        this.reservation = reservation;
        this.createAt = ZonedDateTime.now();
        this.paymentStatus = PaymentStatus.NOTPAID;
    }

    public void updateStatus(PaymentStatus paymentStatus){
        this.paymentStatus = paymentStatus;
    }
}
