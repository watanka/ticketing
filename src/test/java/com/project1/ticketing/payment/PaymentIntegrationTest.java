package com.project1.ticketing.payment;

import com.project1.ticketing.domain.payment.components.PaymentService;
import com.project1.ticketing.domain.payment.components.PaymentValidator;
import com.project1.ticketing.domain.payment.repository.IPaymentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class PaymentIntegrationTest {

    PaymentService paymentService;
    IPaymentRepository paymentRepository;
    PaymentValidator paymentValidator;

//    @BeforeEach
//    void setUp(){
//        paymentRepository = Mockito.mock(IPaymentRepository.class);
//        paymentValidator = Mockito.mock(PaymentValidator.class);
//        paymentService = new PaymentService(paymentRepository, paymentValidator);
//
//        sampleReservation = Mockito.mock(Reservation.class);
//    }

    @Test
    @DisplayName("결제 포인트가 충분할 경우 결제가 진행된다.")
    void case1(){
        long balance = 500000L;
        long seatPrice = 400000;

        paymentValidator.validatePoint(seatPrice, balance);



//        when(sampleReservation.getSeatId()).thenReturn(Seat.builder().price(30000L).build());
//
//        Payment payment = paymentService.pay(sampleReservation, 30000L);
//
//        Assertions.assertThat(payment.getPaymentStatus()).isEqualTo(PaymentStatus.PAID);
    }


    @Test
    @DisplayName("결제 포인트가 부족할 경우, 예약에 실패한다.")
    void case2(){

    }

}
