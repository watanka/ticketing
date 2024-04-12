package com.project1.ticketing.domain.token.components;

import com.project1.ticketing.api.dto.response.TokenResponseDTO;
import com.project1.ticketing.domain.token.infrastructure.MemoryTokenRepository;
import com.project1.ticketing.domain.token.models.Token;
import org.springframework.beans.factory.annotation.Autowired;

public class MemoryTokenService implements ITokenService{

    @Autowired
    MemoryTokenRepository tokenRepository;

    public MemoryTokenService(MemoryTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Token insertQueue(long concertId, String uuid) {
        return null;
    }

    @Override
    public Token getWaitNumByToken(long concertId, String token) {
        return null;
    }
}
