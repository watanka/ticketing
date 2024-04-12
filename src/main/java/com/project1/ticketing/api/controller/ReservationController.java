package com.project1.ticketing.api.controller;

import com.project1.ticketing.api.dto.request.ReservationRequestDTO;
import com.project1.ticketing.api.dto.response.ReservationResponseDTO;
import com.project1.ticketing.domain.reservation.components.IReservationService;
import com.project1.ticketing.domain.reservation.components.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReservationController {

    ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reservations") // token_id, user_id, concert_time, seat_num
    public ResponseEntity<ReservationResponseDTO> makeReservation(@RequestBody ReservationRequestDTO reservationRequestDTO){

//        ReservationResponseDTO reservationResponseDTO = reservationService.register(reservationRequestDTO);
//
//        return ResponseEntity.ok().body(reservationResponseDTO);
        return null;

    }

    @GetMapping("/reservations/users/{user_id}") // user_id, concert_time, seat_num
    public ResponseEntity<ReservationResponseDTO> checkReservation(@PathVariable(value="user_id") long userId){
//        ReservationResponseDTO reservationResponseDTO = reservationService.check(userId);
//
//        return ResponseEntity.ok().body(reservationResponseDTO);
        return null;
    }

}
