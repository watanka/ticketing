package com.project1.ticketing.api.usecase;

import com.project1.ticketing.api.dto.request.TokenRequestDTO;
import com.project1.ticketing.api.dto.response.TokenResponseDTO;
import com.project1.ticketing.domain.token.components.MemoryTokenService;
import com.project1.ticketing.domain.token.models.Token;
import org.springframework.stereotype.Component;

@Component
public class TokenUseCase {

//    MemoryTokenService tokenService;
//
//    public TokenUseCase(MemoryTokenService tokenService) {
//        this.tokenService = tokenService;
//    }
//
//    public TokenResponseDTO insertInQueue(long concertId, TokenRequestDTO){
//        MemoryTokenService.insertInQueue(concertId, );
//
//        return new TokenResponseDTO();
//    }
//
//    public TokenResponseDTO getWaitNumByToken(long concertId, Token token){
//        Token findToken = tokenService.getWaitNumByToken(long concertId, Token token);
//
//        return new TokenResponseDTO(findToken);
//    }
//
//
//
}
