package com.project1.ticketing.domain.token.infrastructure;

import com.project1.ticketing.domain.token.models.Token;
import com.project1.ticketing.domain.token.repository.ITokenRepository;
import com.project1.ticketing.domain.token.repository.TokenJpaRepository;

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


}
