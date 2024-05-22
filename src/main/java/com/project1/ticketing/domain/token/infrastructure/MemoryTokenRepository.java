package com.project1.ticketing.domain.token.infrastructure;

import com.project1.ticketing.domain.token.models.Token;
import com.project1.ticketing.domain.token.models.TokenStatus;
import com.project1.ticketing.domain.token.repository.ITokenRepository;

import java.util.List;
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
    public Token save(Token token) {
        waitQueue.put(token.getUserId(), token);
        return token;

    }

    @Override
    public Token findByUserId(long userId) {
        return waitQueue.get(userId);
    }

    @Override
    public List<Token> findByStatus(TokenStatus status) {
        return null;
    }

//    @Override
//    public List<Token> findTokensOrderByExpiredAt(int n) {
//        return null;
//    }


}
