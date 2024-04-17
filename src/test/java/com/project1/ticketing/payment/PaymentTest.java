package com.project1.ticketing.payment;

import com.project1.ticketing.domain.payment.components.PaymentService;
import com.project1.ticketing.domain.payment.components.PaymentValidator;
import com.project1.ticketing.domain.payment.repository.IPaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PaymentTest {

    PaymentService paymentService;
    IPaymentRepository paymentRepository;
    PaymentValidator paymentValidator;

    @BeforeEach
    void setUp(){
        paymentService = new PaymentService(paymentRepository, paymentValidator)
    }

    @Test
    @DisplayName("예약건을 결제한다.")
    void case1(){

    }


    @Test
    @DisplayName("결제 후 예약 상태를 확인한다.")
    void case2(){

    }

}
