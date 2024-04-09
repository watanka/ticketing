package com.project1.ticketing.api.controller;

import com.project1.ticketing.api.dto.response.*;
import com.project1.ticketing.domain.token.models.Token;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class MockManager {

    Map<Long, String> concertMap = new HashMap<>();
    Map<Long, List<String>> concertTimeMap = new HashMap<>(); // TODO: ConcertTime: String => ZonedDateTime



    Map<Long, Map<String, Token>> waitQueue = new HashMap<>(); // {concert_id : {tokenId : Token}}
    Map<Long, Integer> waitingNumMap = new HashMap<>();

    Map<String, List<Integer>> timeSeatMap = new HashMap<>();


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


    public void initialize(){
        concertMap.put(0L, "나훈아50주년 콘서트");
        concertMap.put(1L, "아일릿 데뷔 콘서트");

        concertTimeMap.put(0L, List.of("04-10","04-13"));
        concertTimeMap.put(1L, List.of("05-05","05-10"));

        timeSeatMap.put("NHS0410", List.of(1, 3, 10, 24, 25));
        timeSeatMap.put("NHS0413", List.of(10, 20, 23,24,25,26));
        timeSeatMap.put("AIL0505", List.of(1, 5, 10, 24, 36, 50));
        timeSeatMap.put("AIL0510", List.of(1, 2));

    }

    public TokenResponseDTO insertInQueue(long concertId, String uuid) {

        Map<String, Token> TokenWaitNumMap = waitQueue.get(concertId);

        if (TokenWaitNumMap==null){
            TokenWaitNumMap = new HashMap<>();
            waitQueue.put(concertId, TokenWaitNumMap);
        }
        long waitingNum = TokenWaitNumMap.size();
        String tokenId = "fakeToken"+waitingNum;

        Token newToken = new Token(uuid, tokenId, ++waitingNum);
        TokenWaitNumMap.put(tokenId, newToken);

        return new TokenResponseDTO(uuid, tokenId, waitingNum);

    }

    public TokenResponseDTO getWaitNumByToken(long concertId, String token) throws Exception {

        if (waitQueue.containsKey(concertId)) {
            Token existToken = waitQueue.get(concertId).get(token);
            return new TokenResponseDTO(existToken.getUuid(), token, existToken.getWaitingNum());
        }else{
            throw new Exception("cannot find tokenID");
        }
    }

    public ConcertResponseDTO getConcertList() {

        return new ConcertResponseDTO(new ArrayList<String>(concertMap.values()));
    }

    public ConcertResponseDTO getAvailableConcertTime(long concertId) {
        List<String> concertTimeList = concertTimeMap.getOrDefault(concertId, new ArrayList<>());

        return new ConcertResponseDTO(concertId, concertTimeList);
    }

    public ConcertResponseDTO getAvailableSeat(long concertId, String concertTime) {

        List<Integer> seatList = timeSeatMap.getOrDefault(concertTime, new ArrayList<>());

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
