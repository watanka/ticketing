package com.project1.ticketing.payment;

import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.payment.components.PaymentService;
import com.project1.ticketing.domain.payment.components.PaymentValidator;
import com.project1.ticketing.domain.payment.models.Payment;
import com.project1.ticketing.domain.payment.models.PaymentStatus;
import com.project1.ticketing.domain.payment.repository.IPaymentRepository;
import com.project1.ticketing.domain.reservation.models.Reservation;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

//public class PaymentTest {
//
//    PaymentService paymentService;
//    IPaymentRepository paymentRepository;
//    PaymentValidator paymentValidator;
//
//    Reservation sampleReservation;
//    @BeforeEach
//    void setUp(){
//        paymentRepository = Mockito.mock(IPaymentRepository.class);
//        paymentValidator = Mockito.mock(PaymentValidator.class);
//        paymentService = new PaymentService(paymentRepository, paymentValidator);
//
//        sampleReservation = Mockito.mock(Reservation.class);
//    }
//
//    @Test
//    @DisplayName("결제 포인트가 충분할 경우, 예약건을 결제한다.")
//    void case1(){
//        when(sampleReservation.getSeatId()).thenReturn(Seat.builder().price(30000L).build());
//
//        Payment payment = paymentService.pay(sampleReservation, 30000L);
//
//        Assertions.assertThat(payment.getPaymentStatus()).isEqualTo(PaymentStatus.PAID);
//    }
//
//
//    @Test
//    @DisplayName("결제 포인트가 부족할 경우, 예약에 실패한다.")
//    void case2(){
//        when(sampleReservation.getSeat()).thenReturn(Seat.builder().price(40000L).build());
//
//        try{
//            Payment payment = paymentService.pay(sampleReservation, 30000L);
//        } catch (RuntimeException e){
//            assertThat(e.getMessage()).isEqualTo("잔액이 부족합니다.");
//
//        }
//    }
//
//}
