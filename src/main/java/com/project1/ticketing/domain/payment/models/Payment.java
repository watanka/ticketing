package com.project1.ticketing.domain.payment.models;

import com.project1.ticketing.domain.common.BaseEntity;
import com.project1.ticketing.domain.reservation.models.Reservation;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Getter @Setter
public class Payment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    long reservationId;
    long seatId;
    long userId;
    long price;
    PaymentStatus paymentStatus;

    @Builder
    public Payment(long reservationId, long seatId, long userId, long price) {
        this.reservationId = reservationId;
        this.seatId = seatId;
        this.userId = userId;
        this.price = price;
        this.paymentStatus = PaymentStatus.NOTPAID;
    }

    public void updateStatus(PaymentStatus paymentStatus){
        this.paymentStatus = paymentStatus;
    }

    public boolean checkBalanceSufficient(long userBalance){
        boolean ableToPay = (userBalance >= this.price);
        return ableToPay;
    }



}
