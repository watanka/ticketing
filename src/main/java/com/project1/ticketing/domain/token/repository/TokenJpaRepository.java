package com.project1.ticketing.domain.token.repository;

import com.project1.ticketing.domain.token.models.Token;
import com.project1.ticketing.domain.token.models.TokenStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenJpaRepository extends JpaRepository<Token, Long> {

    public Token save(Token token);
    public Token findById(long userId);

    public List<Token> findByStatus(TokenStatus tokenStatus);
}
