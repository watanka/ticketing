package com.project1.ticketing.domain.token.repository;

import com.project1.ticketing.domain.token.models.Token;
import com.project1.ticketing.domain.token.models.TokenStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TokenJpaRepository extends JpaRepository<Token, Long> {

    Token save(Token token);
    Token findById(long userId);

    List<Token> findByStatus(TokenStatus tokenStatus);

//    List<Token> findTopNByOrderByExpiredAtAsc(int n);

}
