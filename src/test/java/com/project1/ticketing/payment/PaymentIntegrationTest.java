package com.project1.ticketing.payment;

import com.project1.ticketing.domain.payment.components.PaymentService;
import com.project1.ticketing.domain.payment.components.PaymentValidator;
import com.project1.ticketing.domain.payment.models.Payment;
import com.project1.ticketing.domain.payment.repository.IPaymentRepository;
import com.project1.ticketing.domain.point.models.User;
import com.project1.ticketing.domain.reservation.models.Reservation;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
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

        Reservation reservation = Reservation.builder()
                .price(seatPrice)
                .build();

        Payment payment = Payment.builder()
                .reservation(reservation)
                .build();

        User user = User.builder()
                .balance(balance)
                .build();


        paymentValidator.validatePoint(payment, user);



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
