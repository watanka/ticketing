package com.project1.ticketing.api.controller;


import com.project1.ticketing.api.dto.request.PaymentRequestDTO;
import com.project1.ticketing.api.dto.request.PointRequestDTO;
import com.project1.ticketing.api.dto.request.ReservationRequestDTO;
import com.project1.ticketing.api.dto.response.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MockAPIController {

    MockManager mockManager;

    public MockAPIController(MockManager mockManager) {
        this.mockManager = mockManager;
    }

    /*********
     * TOKEN *
     *********/

    @PostMapping("/queue/{concert_id}")
    public ResponseEntity<TokenResponseDTO> getInLine(@PathVariable(value="concert_id") long concertId, @RequestParam(value="user_id") long userId){

        String token = "AAAA-BBBB-CCCC-DDDD"; // 유저 ID에 따라토큰 임시 부여. TODO: userId와 토큰 맵핑.

        TokenResponseDTO tokenResponseDTO = mockManager.insertInQueue(concertId, userId, token);

        return ResponseEntity.ok().body(tokenResponseDTO);
    }

    @GetMapping("/queue/{concert_id}")
    public ResponseEntity<TokenResponseDTO> checkWaitNum(@PathVariable(value="concert_id") long concertId, @RequestParam(value="user_id") long userId){

        //TODO: 유저ID로 토큰찾는 로직이 필요함
        String token = "AAAA-BBBB-CCCC-DDDD";

        TokenResponseDTO tokenResponseDTO = mockManager.getWaitNumByToken(concertId, userId, token);

        return ResponseEntity.ok().body(tokenResponseDTO);
    }

    /***********
     * CONCERT *
     ***********/

    @GetMapping("/concerts/{concert_id}")
    public ResponseEntity<ConcertResponseDTO> getAvailableConcertTime(@PathVariable(value="concert_id") long concertId){

        ConcertResponseDTO concertResponseDTO = mockManager.getAvailableConcertTime(concertId);

        return ResponseEntity.ok().body(concertResponseDTO);

    }

    @GetMapping("/concerts/{concert_id}")
    public ResponseEntity<ConcertResponseDTO> getAvailableSeat(
            @PathVariable(value="concert_id") long concertId,
            @RequestParam(value="concert_time") String concertTime){

        ConcertResponseDTO concertResponseDTO = mockManager.getAvailableSeat(concertId, concertTime);

        return ResponseEntity.ok().body(concertResponseDTO);

    }

//
//    /***************
//     * RESERVATION *
//     ***************/
//
//    @PostMapping("/reservation") // token_id, user_id, concert_time, seat_num
//    public ResponseEntity<ReservationResponseDTO> makeReservation(@RequestBody ReservationRequestDTO reservationRequestDTO){
//
//        ReservationResponseDTO reservationResponseDTO = mockManager.makeReservation(reservationRequestDTO);
//
//        return ResponseEntity.ok().body(reservationResponseDTO);
//
//    }
//
//    @GetMapping("/reservation/{user_id}") // user_id, concert_time, seat_num
//    public ResponseEntity<ReservationResponseDTO> checkReservation(@PathVariable(value="user_id") long userId){
//        ReservationResponseDTO reservationResponseDTO = mockManager.checkReservation(userId);
//
//        return ResponseEntity.ok().body(reservationResponseDTO);
//    }
//
//
//    /*********
//     * POINT *
//     *********/
//    @PostMapping("/points/{user_id}")
//    public ResponseEntity<PointResponseDTO> charge(@RequestBody PointRequestDTO pointRequestDTO){
//        PointResponseDTO pointResponseDTO = mockManager.chargePoint(pointRequestDTO);
//
//        return ResponseEntity.ok().body(pointResponseDTO);
//    }
//
//    @PostMapping("/points/{user_id}")
//    public ResponseEntity<PointResponseDTO> checkPoint(@RequestBody PointRequestDTO pointRequestDTO){
//        PointResponseDTO pointResponseDTO = mockManager.checkPoint(pointRequestDTO);
//
//        return ResponseEntity.ok().body(pointResponseDTO);
//    }
//
//    /***********
//     * PAYMENT *
//     ***********/
//    @PostMapping("/payment") // Query: reservation_id
//    public ResponseEntity<PaymentResponseDTO> pay(@RequestBody PaymentRequestDTO paymentRequestDTO){
//        PaymentResponseDTO paymentResponseDTO = mockManager.pay(paymentRequestDTO);
//
//        return ResponseEntity.ok().body(paymentResponseDTO);
//
//    }
//
//    @GetMapping("/payment/{user_id}") // Query: reservation_id
//    public ResponseEntity<PaymentResponseDTO> checkPayment(@PathVariable(value="user_id") long userId){
//        PaymentResponseDTO paymentResponseDTO = mockManager.checkPayment(userId);
//
//        return ResponseEntity.ok().body(paymentResponseDTO);
//
//    }

}
