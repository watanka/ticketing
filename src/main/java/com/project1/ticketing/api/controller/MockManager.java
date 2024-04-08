package com.project1.ticketing.api.controller;

import com.project1.ticketing.api.dto.request.PaymentRequestDTO;
import com.project1.ticketing.api.dto.request.PointRequestDTO;
import com.project1.ticketing.api.dto.request.ReservationRequestDTO;
import com.project1.ticketing.api.dto.response.*;
import org.springframework.stereotype.Component;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;


@Component
public class MockManager {

    Map<Long, Map<String, Long>> waitQueue = new HashMap<>(); // 콘서트별로 queue를 따로 둔다.
    Map<Long, Integer> waitingNumMap = new HashMap<>();
    Map<Long, List<String>> concertTimeMap; // TODO: ConcertTime: String => ZonedDateTime
    Map<String, List<Long>> timeSeatMap;


    // put dummy data
//    ArrayList<Long> concertList = new ArrayList<>(Arrays.asList(123L, 234L, 345L, 456L, 567L));
//
//    ArrayList<String> concertTimeList = new ArrayList<>(
//            Arrays.asList(
//                    "2024-01-11-21:00:00",
//                    "2024-01-11-21:00:00",
//                    "2024-01-12-21:00:00",
//                    "2024-01-13-21:00:00",
//                    "2024-01-14-21:00:00"
//            ));
//
//    for (int i = 0; i < concertList.size(); i++) {
//        long concertId = concertList.get(i);
//        String concertTime = concertTimeList.get(i);
//        concertTimeMap.put(concertId, concertTime);
//    }
//
//    // timeSeatMap 초기화
//    for (String time: concertTimeList) {
//    timeSeatMap.put(time, new ArrayList<>());
//    // 좌석 정보를 추가하는 코드
//    // 예시로서 더미 좌석 정보를 추가합니다.
//        for (int i=0;i<50;i++){
//            timeSeatMap.get(time).add((long) i);
//        }
//
//    }
//




    public TokenResponseDTO insertInQueue(long concertId, long userId, String token) {
        Map<String, Long> TokenWaitNumMap = waitQueue.getOrDefault(concertId, new HashMap<>());

        int waitingNum = waitingNumMap.getOrDefault(concertId, 0);

        waitingNumMap.put(concertId, ++waitingNum);

        return new TokenResponseDTO(userId, token, waitingNum);

    }

    public TokenResponseDTO getWaitNumByToken(long concertId, long userId, String token) {
         long waitingNum = waitQueue.get(concertId).getOrDefault(token, 0L);

         return new TokenResponseDTO(userId, token, waitingNum);
    }

    public ConcertResponseDTO getAvailableConcertTime(long concertId) {
        List<String> concertTimeList = concertTimeMap.getOrDefault(concertId, new ArrayList<>());

        return new ConcertResponseDTO(concertId, concertTimeList);
    }

    public ConcertResponseDTO getAvailableSeat(long concertId, String concertTime) {

        List<Long> seatList = timeSeatMap.getOrDefault(concertTime, new ArrayList<>());

        return new ConcertResponseDTO(concertId, concertTime, seatList);
    }

//    public ReservationResponseDTO makeReservation(ReservationRequestDTO reservationRequestDTO) {
//
//    }
//
//    public ReservationResponseDTO checkReservation(long userId) {
//    }
//
//    public PointResponseDTO chargePoint(PointRequestDTO pointRequestDTO) {
//    }
//
//    public PointResponseDTO checkPoint(PointRequestDTO pointRequestDTO) {
//    }
//
//    public PaymentResponseDTO pay(PaymentRequestDTO paymentRequestDTO) {
//    }
//
//    public PaymentResponseDTO checkPayment(long userId) {
//
//
//    }



}
