package com.project1.ticketing.domain.token.components;

import com.project1.ticketing.domain.token.models.Token;

public interface ITokenService {
    Token insertQueue(long concertId, String uuid);
    Token getWaitNumByToken(long concertId, String token);
}
