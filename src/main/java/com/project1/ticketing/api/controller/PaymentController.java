package com.project1.ticketing.api.controller;

import com.project1.ticketing.api.dto.request.PaymentRequestDTO;
import com.project1.ticketing.api.dto.response.PaymentResponseDTO;
import com.project1.ticketing.domain.payment.components.IPaymentService;
import com.project1.ticketing.domain.payment.components.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    PaymentService paymentService;


    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payment") // Query: reservation_id
    public ResponseEntity<PaymentResponseDTO> pay(@RequestBody PaymentRequestDTO paymentRequestDTO){
//        PaymentResponseDTO paymentResponseDTO = paymentService.pay(paymentRequestDTO);
//
//        return ResponseEntity.ok().body(paymentResponseDTO);
        return null;
    }

    @GetMapping("/payment/{user_id}/reservations/{reservation_id}") // Query: reservation_id
    public ResponseEntity<PaymentResponseDTO> checkPayment(@PathVariable(value="user_id") long userId,
                                                           @PathVariable(value="reservation_id") long reservationId
    ){
//        PaymentResponseDTO paymentResponseDTO = paymentService.checkPayment(userId, reservationId);
//
//        return ResponseEntity.ok().body(paymentResponseDTO);
        return null;

    }
}
