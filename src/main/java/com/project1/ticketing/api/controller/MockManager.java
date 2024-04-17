package com.project1.ticketing.api.controller;

import com.project1.ticketing.api.dto.request.PaymentRequest;
import com.project1.ticketing.api.dto.request.PointRequest;
import com.project1.ticketing.api.dto.request.ReservationRequest;
import com.project1.ticketing.api.dto.response.*;
import com.project1.ticketing.domain.payment.models.Payment;
import com.project1.ticketing.domain.point.models.PointType;
import com.project1.ticketing.domain.reservation.models.Reservation;
import com.project1.ticketing.domain.reservation.models.ReservationStatus;
import com.project1.ticketing.domain.token.models.Token;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Component
public class MockManager {

    Map<Long, String> concertMap = new HashMap<>();
    Map<Long, List<Long>> concertTimeMap = new HashMap<>(); // TODO: ConcertTime: String => ZonedDateTime



    Map<Long, Map<String, Token>> waitQueue = new HashMap<>(); // {concert_id : {tokenId : Token}}
    Map<Long, Integer> waitingNumMap = new HashMap<>();

    Map<String, List<Integer>> timeSeatMap = new HashMap<>();

    long reservationNum = 0L;
    Map<Long, Map<Long, Reservation>> reservationMap = new HashMap<>();
    Map<Long, Long> pointMap = new HashMap<>();

    Map<Long, Map<Long, Payment>> paymentMap = new HashMap<>();


    public void initialize(){
        concertMap.put(0L, "나훈아50주년 콘서트");
        concertMap.put(1L, "아일릿 데뷔 콘서트");

//        concertTimeMap.put(0L, List.of("04-10","04-13"));
//        concertTimeMap.put(1L, List.of("05-05","05-10"));

        timeSeatMap.put("NHS0410", List.of(1, 3, 10, 24, 25));
        timeSeatMap.put("NHS0413", List.of(10, 20, 23,24,25,26));
        timeSeatMap.put("AIL0505", List.of(1, 5, 10, 24, 36, 50));
        timeSeatMap.put("AIL0510", List.of(1, 2));

    }
//
//    public TokenResponseDTO insertInQueue(long concertId, String uuid) {
//
//        Map<String, Token> TokenWaitNumMap = waitQueue.get(concertId);
//
//        if (TokenWaitNumMap==null){
//            TokenWaitNumMap = new HashMap<>();
//            waitQueue.put(concertId, TokenWaitNumMap);
//        }
//        long waitingNum = TokenWaitNumMap.size();
//        String tokenId = "fakeToken"+waitingNum;
//
//        Token newToken = new Token(uuid, tokenId, ++waitingNum);
//        TokenWaitNumMap.put(tokenId, newToken);
//
//        return new TokenResponseDTO(uuid, tokenId, waitingNum);
//
//    }
//
//    public TokenResponseDTO getWaitNumByToken(long concertId, String token) throws Exception {
//
//        if (waitQueue.containsKey(concertId)) {
//            Token existToken = waitQueue.get(concertId).get(token);
//            return new TokenResponseDTO(existToken.getUuid(), token, existToken.getWaitingNum());
//        }else{
//            throw new Exception("cannot find tokenID");
//        }
//    }

//    public ConcertResponseDTO getConcertList() {
//
//        return new ConcertResponseDTO(new ArrayList<String>(concertMap.values()));
//    }
//
//    public ConcertResponseDTO getAvailableConcertTime(long concertId) {
//        List<Long> concertTimeList = concertTimeMap.getOrDefault(concertId, new ArrayList<>());
//
//        return new ConcertResponseDTO(concertId, concertTimeList);
//    }
//
//    public ConcertResponseDTO getAvailableSeat(long concertId, String concertTime) {
//
//        List<Integer> seatList = timeSeatMap.getOrDefault(concertTime, new ArrayList<>());
//
//        return new ConcertResponseDTO(concertId, concertTime, seatList);
//    }


//
//    public ReservationResponse makeReservation(ReservationRequest reservationRequest) {
//        long userId = reservationRequest.getUserId();
//
//        Reservation reservation = new Reservation(
//                ++reservationNum,
//                userId,
//                reservationRequest.getConcertTime(),
//                reservationRequest.getSeatId(),
//                50000, // TODO: need price table for each concertTime
//                ReservationStatus.TEMPORARY_RESERVED,
//                ZonedDateTime.now().plus(5, ChronoUnit.MINUTES)
//
//                );
//
//        Map<Long, Reservation> reservationById = reservationMap.getOrDefault(userId, new HashMap<Long, Reservation>());
//        reservationById.put(reservationNum, reservation);
//        reservationMap.put(userId, reservationById);
//
//        return new ReservationResponse(
//                userId,
//                List.of(reservation)
//        );
//
//    }
//
//    public ReservationResponse checkReservation(long userId) {
//        List<Reservation> reservationList = new ArrayList<>(reservationMap.get(userId).values());
//
//        return new ReservationResponse(userId, reservationList);
//    }
//
//    public PointResponse updatePoint(PointRequest pointRequest) {
//        long userId = pointRequest.getUserId();
//        long amount = pointRequest.getAmount();
//        PointType pointType = pointRequest.getPointType();
//
//        long remainingPoint = pointMap.getOrDefault(userId, 0L);
//
//        if (pointType.equals(PointType.CHARGE)){
//            remainingPoint += amount;
//        }else {
//            //TODO: 사용금액보다 잔액이 부족할 때 에러 발생.
//            remainingPoint -= amount;
//        }
//
//        pointMap.put(userId, remainingPoint);
//
//        return new PointResponse(userId, remainingPoint);
//
//
//
//
//    }
//
//    public PointResponse checkPoint(long userId) {
//        long point = pointMap.getOrDefault(userId, 0L);
//
//        return new PointResponse(userId, point);
//    }
//
//    public PaymentResponse pay(PaymentRequest paymentRequest) {
//        //userid로 포인트 조회
//        //reservation 유효성 검증. token expiredAt 확인
//
//        long userId = paymentRequest.getUserId();
//        long amount = pointMap.get(userId);
//
//
//        long reservationId = paymentRequest.getReservationId();
//
//        Map <Long, Reservation> reservationByUser = reservationMap.get(userId);
//
//        Reservation reservation = reservationByUser.get(reservationId);
//
//        if (reservation == null) {
//            System.out.println("could not find reservation id " + reservationId);
//
//        }
//            if (amount > reservation.getPrice()){
//                amount -= reservation.getPrice();
//                reservation.setStatus(ReservationStatus.REGISTERED);
//                reservationByUser.put(reservationId, reservation);
//                reservationMap.put(userId, reservationByUser);
//        } else {
//                System.out.println("Not Enough Money. redirect to /points 충전.");
//            }
//        Payment payment = new Payment(userId, reservation);
//
//        return new PaymentResponse(userId, payment);
//    }
//
//    public PaymentResponse checkPayment(long userId, long reservationId) {
//        reservationMap.get(userId).get(reservationId);
//
//        return new PaymentResponse(userId, paymentMap.get(userId).get(reservationId));
//
//    }
//
//

}
