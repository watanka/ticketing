package com.project1.ticketing.domain.token.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ConnectionPoolConfig;
import redis.clients.jedis.JedisPooled;

import java.util.List;
import java.util.Optional;

@Repository
public class TokenJedisRepository implements ITokenRepository {
    private final String url;
    private final int port;

    private final JedisPooled jedis;


    @Autowired
    public TokenJedisRepository(ConnectionPoolConfig jedisPoolConfig,
                                @Value("${spring.redis.url}") String url,
                                @Value("${spring.redis.port}") int port) {

        this.url = url;
        this.port = port;
        this.jedis = new JedisPooled(jedisPoolConfig, url, port);

    }

    @Override
    public long insert(String keyName, String token, long currentTimeMillis){
        // key : token
        // rank: issuedAt
        // member: userId
        return jedis.zadd(keyName, currentTimeMillis, token);

    }

    @Override
    public void remove(String keyName, String token){
        jedis.zrem(keyName, token);
    }

    @Override
    public void removeAll(){
        jedis.flushDB();
    }

    @Override
    public boolean checkTokenInQueue(String keyName, String token){
        Optional<Long> rankOfToken = Optional.ofNullable(jedis.zrank(keyName, token));
        return rankOfToken.isPresent();

    }

    @Override
    public List<String> getTokenInRange(String keyName, long start, long end){
        return jedis.zrange(keyName, start, end);
    }

    @Override
    public long removeTokensInRange(String keyName, long start, long end){
        return jedis.zremrangeByRank(keyName, start, end);
    }

    @Override
    public long getWaitingNum(String keyName, String token) {
        return jedis.zrank(keyName, token);
    }

    @Override
    public long getTotalLength(String keyName){
        return jedis.zcard(keyName);
    }


}
