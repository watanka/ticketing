package com.project1.ticketing.domain.payment.components;

import com.project1.ticketing.domain.payment.models.PaymentStatus;
import com.project1.ticketing.domain.reservation.models.ReservationStatus;
import org.springframework.stereotype.Component;

@Component
public class PaymentValidator {

    // 예약 시간 지남

    //
    public void validate(long balance, long price){
        if (price > balance){
            throw new RuntimeException("잔액이 부족합니다.");
        }
    }
}
