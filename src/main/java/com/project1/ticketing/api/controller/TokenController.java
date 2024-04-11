package com.project1.ticketing.api.controller;

import com.project1.ticketing.api.dto.request.TokenRequestDTO;
import com.project1.ticketing.api.dto.response.TokenResponseDTO;
import com.project1.ticketing.api.usecase.TokenUseCase;
import com.project1.ticketing.domain.token.components.ITokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class TokenController {

    TokenUseCase tokenUseCase;

    public TokenController(ITokenService tokenUseCase) {
        this.tokenUseCase = tokenUseCase;
    }


    @PostMapping("/tokens/concerts/{concert_id}")
    public ResponseEntity<TokenResponseDTO> getInLine(@PathVariable(value="concert_id") long concertId,
                                                      @RequestBody TokenRequestDTO tokenRequestDTO){
        System.out.println("concertId: " + concertId);
        String uuid = tokenRequestDTO.getUuid();


        TokenResponseDTO tokenResponseDTO = tokenUseCase.insertInQueue(concertId, uuid);

        return ResponseEntity.ok().body(tokenResponseDTO);
    }

    @GetMapping("/tokens/concerts/{concert_id}")
    public ResponseEntity<TokenResponseDTO> checkWaitNum(@PathVariable(value="concert_id") long concertId,
                                                         @RequestHeader("Authorization") String token) throws Exception {

        //TODO: 유저ID로 토큰찾는 로직이 필요함

        TokenResponseDTO tokenResponseDTO = tokenUseCase.getWaitNumByToken(concertId, token);
        return ResponseEntity.ok().body(tokenResponseDTO);
    }
}
