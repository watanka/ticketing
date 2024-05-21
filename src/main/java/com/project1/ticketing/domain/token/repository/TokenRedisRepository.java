package com.project1.ticketing.domain.token.repository;

import java.time.ZonedDateTime;
import java.util.List;

public interface TokenRedisRepository {
    long register(long userId);

    long countActiveTokens();

    void activate(long userId, ZonedDateTime expiredTime);

    List<Long> findUserToActivate();


    void expire(long userIds);

}
