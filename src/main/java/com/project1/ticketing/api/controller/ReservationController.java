package com.project1.ticketing.api.controller;

import com.project1.ticketing.api.dto.request.ReservationRequest;
import com.project1.ticketing.api.dto.response.ReservationResponse;
import com.project1.ticketing.domain.reservation.components.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReservationController {

    ReservationService reservationService;

//    @Autowired
//    public ReservationController(ReservationService reservationService) {
//        this.reservationService = reservationService;
//    }

    @PostMapping("/reservations") // token_id, user_id, concert_time, seat_num
    public ResponseEntity<ReservationResponse> makeReservation(@RequestBody ReservationRequest reservationRequest){

//        ReservationResponse reservationResponse = reservationService.register(reservationRequest);
//
//        return ResponseEntity.ok().body(reservationResponseDTO);
        return null;

    }

    @GetMapping("/reservations/users/{user_id}") // user_id, concert_time, seat_num
    public ResponseEntity<ReservationResponse> checkReservation(@PathVariable(value="user_id") long userId){
//        ReservationResponse reservationResponse = reservationService.check(userId);
//
//        return ResponseEntity.ok().body(reservationResponseDTO);
        return null;
    }

}
