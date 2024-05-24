package com.project1.ticketing.domain.payment.components;

import com.project1.ticketing.domain.payment.models.Payment;
import com.project1.ticketing.domain.point.models.User;
import org.springframework.stereotype.Component;

@Component
public class PaymentValidator {

    // 예약 시간 지남

    //
    public void validatePoint(Payment payment, User user){
        boolean ableToPay = payment.checkBalanceSufficient(user.getBalance());
        if (!ableToPay){
            throw new RuntimeException("잔액이 부족합니다.");
        }
    }
}
