package com.project1.ticketing.domain.token.infrastructure;

import com.project1.ticketing.domain.token.models.Token;
import com.project1.ticketing.domain.token.repository.ITokenRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


//
public class MemoryTokenRepository implements ITokenRepository {
//
//    Map<Long, Map<String, Token>> waitQueue = new HashMap<>(); // {concert_id : {tokenId : Token}}
//    Map<Long, Integer> waitingNumMap = new HashMap<>();
    Map<Long, Token> waitQueue = new ConcurrentHashMap<>();
    AtomicInteger waitingNum;

    @Override
    public Token save(long userId) {

        Token token = Token.builder()
                            .tokenId(waitingNum.incrementAndGet())
                            .userId(userId)
                            .isExpired(false)
                            .build();



        waitQueue.put(userId, token);
        return token;

    }

    @Override
    public Token findByUserId(long userId) {
        return waitQueue.get(userId);
    }
}
