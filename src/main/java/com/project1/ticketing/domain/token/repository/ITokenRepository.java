package com.project1.ticketing.domain.token.repository;

import com.project1.ticketing.domain.token.models.Token;

public class ITokenRepository {

    public Token save(long concertId, String uuid);
    public Token findById(long concertId, String token);
}
