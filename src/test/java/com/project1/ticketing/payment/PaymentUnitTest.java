package com.project1.ticketing.payment;

import com.project1.ticketing.domain.payment.models.Payment;
import com.project1.ticketing.domain.reservation.models.Reservation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

public class PaymentUnitTest {

    @Test
    @DisplayName("충분한 포인트가 있다면 결제에 성공한다.")
    void case1(){
        long price = 500000;
        long userBalance = 500000;
        long userId = 1L;
        long concertId = 0;
        long concertTime = 0;
        long seatNum = 0;
        long seatId = 1L;
        ZonedDateTime expiredAt = ZonedDateTime.now();


        Reservation reservation = new Reservation(userId, concertId, concertTime, seatId, seatNum, price, expiredAt);

        Payment payment = new Payment(reservation);

        Assertions.assertThat(payment.checkBalanceSufficient(userBalance)).isTrue();
    }

    @Test
    @DisplayName("포인트가 부족하다면 결제에 실패한다.")
    void case2(){
        long price = 500000;
        long userBalance = 300000;
        long userId = 1L;
        long concertId = 0;
        long concertTime = 0;
        long seatNum = 0;
        long seatId = 1L;
        ZonedDateTime expiredAt = ZonedDateTime.now();


        Reservation reservation = new Reservation(userId, concertId, concertTime, seatId, seatNum, price, expiredAt);


        Payment payment = new Payment(reservation);

        Assertions.assertThat(payment.checkBalanceSufficient(userBalance)).isFalse();
    }

}
