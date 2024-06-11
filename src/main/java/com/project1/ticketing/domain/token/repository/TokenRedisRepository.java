package com.project1.ticketing.domain.token.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TokenRedisRepository implements ITokenRepository {

    // [대기열]: SortedSet
    // [활성화 토큰리스트]: Set
    private final String waitingQueueName = "waiting_queue";

    private final String activatedQueueName = "activated_queue";

    @Override
    public String insert(String token, long userId) {
        // 대기열 삽입 ZADD {}
        // key: token, rank: 현재시간, member: userId

        return null;
    }

    @Override
    public String findByToken(String token) {
        // 대기열 keyList 조회

        return null;
    }

    @Override
    public List<String> getActivateUserList(long numActivate) {
        // ZRANGE 0 numActivate [대기열]
        // ZSET activateList [활성화 토큰리스트]

        return null;
    }

    @Override
    public Long getWaitingNum(String token) {
        // rank 조회

        return null;
    }
}
