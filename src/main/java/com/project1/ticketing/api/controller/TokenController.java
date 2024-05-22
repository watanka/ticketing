package com.project1.ticketing.api.controller;

import com.project1.ticketing.api.dto.request.TokenRequest;
import com.project1.ticketing.api.dto.response.TokenResponse;
import com.project1.ticketing.api.usecase.TokenUseCase;
import com.project1.ticketing.domain.token.components.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;


    @PostMapping("/tokens")
    public ResponseEntity<TokenResponse> queue(@RequestBody TokenRequest tokenRequest){

        TokenResponse tokenResponse = tokenService.queue(tokenRequest.userId());
        return ResponseEntity.ok().body(tokenResponse);

    }


    @GetMapping("/tokens")
    public ResponseEntity<TokenResponse> checkWaitNum(@RequestBody TokenRequest tokenRequest){
        TokenResponse tokenResponse = tokenService.checkWaitingNum(tokenRequest.userId());
        return ResponseEntity.ok().body(tokenResponse);
    }
}
//