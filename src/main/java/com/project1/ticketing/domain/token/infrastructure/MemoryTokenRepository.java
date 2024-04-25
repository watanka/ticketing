package com.project1.ticketing.domain.token.infrastructure;

import com.project1.ticketing.domain.token.models.Token;
import com.project1.ticketing.domain.token.repository.ITokenRepository;


//
public class MemoryTokenRepository implements ITokenRepository {
//
//    Map<Long, Map<String, Token>> waitQueue = new HashMap<>(); // {concert_id : {tokenId : Token}}
//    Map<Long, Integer> waitingNumMap = new HashMap<>();


    public void save(long concertId, String uuid){
//        Token Token = new Token(concertId, uuid);
//
//        Map<String, Token> tokenByConcerts = waitQueue.getOrDefault(concertId, new HashMap<>());
//        tokenByConcerts.put()
//
    }
//
    public Token findById(long concertId, String token){
        return null;
    }



}
