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

        tokenService.
//
//        TokenResponseDTO tokenResponseDTO = tokenUseCase.insertInQueue(concertId, uuid);
//
        return null;
//        return ResponseEntity.ok().body(tokenResponseDTO);
    }
//
    @GetMapping("/tokens/")
    public ResponseEntity<TokenResponse> checkWaitNum(@RequestHeader("Authorization") String token) throws Exception {
//
//        //TODO: 유저ID로 토큰찾는 로직이 필요함
//
//        TokenResponseDTO tokenResponseDTO = tokenUseCase.getWaitNumByToken(concertId, token);
//        return ResponseEntity.ok().body(tokenResponseDTO);
        return null;
    }
}
//