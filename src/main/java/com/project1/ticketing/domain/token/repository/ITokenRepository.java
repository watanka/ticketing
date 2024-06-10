package com.project1.ticketing.domain.token.repository;

import com.project1.ticketing.domain.token.models.Token;
import com.project1.ticketing.domain.token.models.TokenStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITokenRepository {

    Token save(Token token);
    Token findByToken(String token);

    List<Token> findByStatus(TokenStatus status);

//    List<Token> findTokensOrderByExpiredAt(int n);
}
