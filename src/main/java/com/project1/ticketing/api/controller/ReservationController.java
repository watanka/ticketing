package com.project1.ticketing.api.controller;

import com.project1.ticketing.api.dto.request.ReservationRequest;
import com.project1.ticketing.api.dto.response.ReservationResponse;
import com.project1.ticketing.domain.reservation.components.IReservationService;
import com.project1.ticketing.domain.reservation.components.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {

    IReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reservations") // token_id, user_id, concert_time, seat_num
    public ResponseEntity<ReservationResponse> makeReservation(@RequestBody ReservationRequest reservationRequest){

        ReservationResponse reservationResponse = reservationService.register(reservationRequest);
        return ResponseEntity.ok().body(reservationResponse);

    }

    @GetMapping("/reservations/users/{user_id}") // user_id, concert_time, seat_num
    public ResponseEntity<List<ReservationResponse>> checkReservationList(
            @PathVariable(value="user_id") long userId){
        List<ReservationResponse> reservationResponseList = reservationService.checkReservationList(userId);
        return ResponseEntity.ok().body(reservationResponseList);
    }

    @GetMapping("/reservations/{reservation_id}/users/{user_id}/") // user_id, concert_time, seat_num
    public ResponseEntity<ReservationResponse> checkReservationInfo(
                             @PathVariable(value="user_id") long userId,
                             @PathVariable(value="reservation_id") long reservationId) {
        ReservationResponse reservationResponse = reservationService.check(userId, reservationId);
        return ResponseEntity.ok().body(reservationResponse);
    }
}
