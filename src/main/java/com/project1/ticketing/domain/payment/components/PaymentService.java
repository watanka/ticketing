package com.project1.ticketing.domain.payment.components;

import com.project1.ticketing.api.dto.request.PointRequest;
import com.project1.ticketing.domain.concert.components.ConcertService;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.models.SeatStatus;
import com.project1.ticketing.domain.concert.repository.ConcertCoreRepository;
import com.project1.ticketing.domain.payment.models.Payment;
import com.project1.ticketing.domain.payment.models.PaymentStatus;
import com.project1.ticketing.domain.payment.repository.IPaymentRepository;
import com.project1.ticketing.domain.payment.repository.PaymentJpaRepository;
import com.project1.ticketing.domain.point.components.PointHistoryService;
import com.project1.ticketing.domain.point.models.PointType;
import com.project1.ticketing.domain.point.models.User;
import com.project1.ticketing.domain.point.repository.PointCoreRepository;
import com.project1.ticketing.domain.point.repository.UserRepository;
import com.project1.ticketing.domain.reservation.components.ReservationService;
import com.project1.ticketing.domain.reservation.models.Reservation;
import com.project1.ticketing.domain.reservation.models.ReservationStatus;
import com.project1.ticketing.domain.reservation.repository.ReservationCoreRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PaymentService{
    IPaymentRepository paymentRepository;
    ReservationService reservationService;
    ConcertCoreRepository concertRepository;
    ConcertService concertService;

    PointHistoryService pointHistoryService;
    PaymentValidator paymentValidator;

    public PaymentService(IPaymentRepository paymentRepository, ReservationService reservationService, ConcertCoreRepository concertRepository, ConcertService concertService, PointHistoryService pointHistoryService, PaymentValidator paymentValidator) {
        this.paymentRepository = paymentRepository;
        this.concertRepository = concertRepository;
        this.concertService = concertService;
        this.pointHistoryService = pointHistoryService;
        this.paymentValidator = paymentValidator;
        this.reservationService = reservationService;
    }

    public Payment pay(long reservationId) {

        Reservation reservation = reservationService.findByReservationId(reservationId);
        Seat seat = concertRepository.findSeatById(reservation.getSeatId());
        long price = seat.getPrice();

        User user = pointHistoryService.findUser(reservation.getUserId());

        // payment 생성하는 로직 분리?
        Payment payment = new Payment(  reservationId,
                                        reservation.getSeatId(),
                                        reservation.getUserId(),
                                        price,
                                        PaymentStatus.NOTPAID);

        paymentValidator.validate(price, user.getBalance());

        // 유저 포인트 차감
        // 포인트 내역 생성
        pointHistoryService.updatePoint(new PointRequest(user.getId(), price, PointType.USE));

        // 좌석 정보 변경 // 예약시 TEMPORARY 상태없이 바로 RESERVED 상태이기 때문에 따로 변경 안해도되긴 함.
        concertService.patchSeatStatus(seat.getId(), SeatStatus.RESERVED);

        // 예약 정보 변경
        reservationService.updateSingleReservationStatus(reservation, ReservationStatus.REGISTERED);


        payment.updateStatus(PaymentStatus.PAID);
        paymentRepository.save(payment);

        return payment;
    }

    public Payment cancel(Reservation reservation){
        return null;
    }

    public Payment checkPayment(long paymentId) {
        return paymentRepository.findById(paymentId);
    }
}
