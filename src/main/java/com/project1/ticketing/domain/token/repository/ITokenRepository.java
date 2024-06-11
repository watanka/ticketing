package com.project1.ticketing.domain.token.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITokenRepository {

    String insert(String token, long userId);

    String findByToken(String token);

    List<Long> getActivateUserList(long numActivate);

    Long getWaitingNum(String token);
}
