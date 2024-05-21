package com.project1.ticketing.domain.token.repository;

import com.project1.ticketing.domain.token.models.Token;
import com.project1.ticketing.domain.token.models.TokenStatus;
import java.util.List;

public interface ITokenRepository {

    public Token save(Token token);
    public Token findByUserId(long userId);

    public List<Token> findByStatus(TokenStatus status);
}
