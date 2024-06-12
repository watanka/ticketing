package com.project1.ticketing.domain.token.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITokenRepository {

    long insert(String keyName, String token, long currentTimeMillis);

    void remove(String keyName, String token);

    boolean checkTokenInQueue(String keyName, String token);


    long removeTokensInRange(String keyName, long start, long end);

    List<String> getTokenInRange(String keyName, long start, long end);

    long getWaitingNum(String keyName, String token);

    void removeAll();

    long getTotalLength(String keyName);
}
