package com.project1.ticketing.domain.payment.models;

import com.project1.ticketing.domain.reservation.models.Reservation;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class Payment {

    long userId;
    Reservation reservation;
    ZonedDateTime createAt;

    public Payment(long userId, Reservation reservation) {
        this.userId = userId;
        this.reservation = reservation;
        this.createAt = ZonedDateTime.now();
    }
}
