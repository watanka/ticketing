package com.project1.ticketing.domain.token.infrastructure;

import com.project1.ticketing.domain.token.models.Token;
import com.project1.ticketing.domain.token.models.TokenStatus;
import com.project1.ticketing.domain.token.repository.ITokenRepository;
import com.project1.ticketing.domain.token.repository.TokenJpaRepository;

import java.util.List;

public class TokenRepositoryJpaImpl implements ITokenRepository {

    TokenJpaRepository tokenJpaRepository;
    @Override
    public Token save(Token token) {
        return tokenJpaRepository.save(token);
    }

    @Override
    public Token findByUserId(long userId) {
        return tokenJpaRepository.findById(userId);
    }

    @Override
    public List<Token> findByStatus(TokenStatus status) {
        return tokenJpaRepository.findByStatus(status);
    }


}
