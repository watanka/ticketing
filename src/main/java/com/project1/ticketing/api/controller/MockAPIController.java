package com.project1.ticketing.api.controller;
import com.project1.ticketing.api.dto.request.PaymentRequestDTO;
import com.project1.ticketing.api.dto.request.PointRequestDTO;
import com.project1.ticketing.api.dto.request.ReservationRequestDTO;
import com.project1.ticketing.api.dto.request.TokenRequestDTO;
import com.project1.ticketing.api.dto.response.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//TODO: 토큰인증, 예외처리

@RestController
public class MockAPIController {

    MockManager mockManager;

    public MockAPIController(MockManager mockManager) {
        this.mockManager = mockManager;
    }

    /*********
     * TOKEN *
     *********/

    @PostMapping("/tokens/concerts/{concert_id}")
    public ResponseEntity<TokenResponseDTO> getInLine(@PathVariable(value="concert_id") long concertId,
                                                      @RequestBody TokenRequestDTO tokenRequestDTO){
        System.out.println("concertId: " + concertId);
        String uuid = tokenRequestDTO.getUuid();


        TokenResponseDTO tokenResponseDTO = mockManager.insertInQueue(concertId, uuid);

        return ResponseEntity.ok().body(tokenResponseDTO);
    }

    @GetMapping("/tokens/concerts/{concert_id}")
    public ResponseEntity<TokenResponseDTO> checkWaitNum(@PathVariable(value="concert_id") long concertId,
                                                         @RequestHeader("Authorization") String token) throws Exception {

        //TODO: 유저ID로 토큰찾는 로직이 필요함

        TokenResponseDTO tokenResponseDTO = mockManager.getWaitNumByToken(concertId, token);
        return ResponseEntity.ok().body(tokenResponseDTO);
    }

    /***********
     * CONCERT *
     ***********/

    @GetMapping("/concerts")
    public ConcertResponseDTO getConcertList(){
         return mockManager.getConcertList();
    }

    @GetMapping("/concerts/{concert_id}/concert-times")
    public ResponseEntity<ConcertResponseDTO> getAvailableConcertTime(@PathVariable(value="concert_id") long concertId){

        ConcertResponseDTO concertResponseDTO = mockManager.getAvailableConcertTime(concertId);

        return ResponseEntity.ok().body(concertResponseDTO);

    }

    @GetMapping("/concerts/{concert_id}/concert-times/{concert_time}/seats")
    public ResponseEntity<ConcertResponseDTO> getAvailableSeat(
            @PathVariable(value="concert_id") long concertId,
            @PathVariable(value="concert_time") String concertTime){

        ConcertResponseDTO concertResponseDTO = mockManager.getAvailableSeat(concertId, concertTime);

        return ResponseEntity.ok().body(concertResponseDTO);

    }


    /***************
     * RESERVATION *
     ***************/

    @PostMapping("/reservations") // token_id, user_id, concert_time, seat_num
    public ResponseEntity<ReservationResponseDTO> makeReservation(@RequestBody ReservationRequestDTO reservationRequestDTO){

        ReservationResponseDTO reservationResponseDTO = mockManager.makeReservation(reservationRequestDTO);

        return ResponseEntity.ok().body(reservationResponseDTO);

    }

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

//    @GetMapping("/payment/{user_id}") // Query: reservation_id
//    public ResponseEntity<PaymentResponseDTO> checkPayment(@PathVariable(value="user_id") long userId){
//        PaymentResponseDTO paymentResponseDTO = mockManager.checkPayment(userId);
//
//        return ResponseEntity.ok().body(paymentResponseDTO);
//
//    }

}
