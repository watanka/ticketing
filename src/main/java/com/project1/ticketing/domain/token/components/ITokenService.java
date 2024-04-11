package com.project1.ticketing.domain.token.components;

import com.project1.ticketing.api.dto.response.TokenResponseDTO;

public interface ITokenService {
    public TokenResponseDTO insertInQueue(long concertId, String uuid);
    public TokenResponseDTO getWaitNumByToken(long concertId, String token);
}
