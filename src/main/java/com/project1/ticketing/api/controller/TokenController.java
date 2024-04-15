package com.project1.ticketing.api.controller;

import com.project1.ticketing.api.dto.request.TokenRequest;
import com.project1.ticketing.api.dto.response.TokenResponse;
import com.project1.ticketing.api.usecase.TokenUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class TokenController {

    TokenUseCase tokenUseCase;

    @Autowired
    public TokenController(TokenUseCase tokenUseCase) {
        this.tokenUseCase = tokenUseCase;
    }


    @PostMapping("/tokens/concerts/{concert_id}")
    public ResponseEntity<TokenResponse> getInLine(@PathVariable(value="concert_id") long concertId,
                                                   @RequestBody TokenRequest tokenRequest){
//        System.out.println("concertId: " + concertId);
//        String uuid = tokenRequestDTO.getUuid();
//
//
//        TokenResponseDTO tokenResponseDTO = tokenUseCase.insertInQueue(concertId, uuid);
//
        return null;
//        return ResponseEntity.ok().body(tokenResponseDTO);
    }
//
    @GetMapping("/tokens/concerts/{concert_id}")
    public ResponseEntity<TokenResponse> checkWaitNum(@PathVariable(value="concert_id") long concertId,
                                                      @RequestHeader("Authorization") String token) throws Exception {
//
//        //TODO: 유저ID로 토큰찾는 로직이 필요함
//
//        TokenResponseDTO tokenResponseDTO = tokenUseCase.getWaitNumByToken(concertId, token);
//        return ResponseEntity.ok().body(tokenResponseDTO);
        return null;
    }
}
//