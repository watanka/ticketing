package com.project1.ticketing.domain.token.components;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JedisService {
    private final String url;
    private final int port;

    private final JedisPooled jedis;


    @Autowired
    public JedisService(ConnectionPoolConfig jedisPoolConfig,
                        @Value("${spring.redis.url}") String url,
                        @Value("${spring.redis.port}") int port) {

        this.url = url;
        this.port = port;
        this.jedis = new JedisPooled(jedisPoolConfig, url, port);

    }

    public long insert(String keyName, String token, long userId, long currentTimeMillis){
        // key : token
        // rank: issuedAt
        // member: userId
        long queueOrder = jedis.zadd(keyName, currentTimeMillis, token);

        return queueOrder;
    }

    public void remove(String keyName, String token){
        jedis.zrem(keyName, token);
    }

    public void removeAll(){
        jedis.flushDB();
    }

    public boolean checkTokenInQueue(String keyName, String token){
        Optional<Long> rankOfToken = Optional.ofNullable(jedis.zrank(keyName, token));
        return rankOfToken.isPresent();

    }

    public List<String> getTokenInRange(String keyName, long start, long end){
        return jedis.zrange(keyName, start, end);
    }

    public long removeTokensInRange(String keyName, long start, long end){
        return jedis.zremrangeByRank(keyName, start, end);
    }

    public long getTotalLength(String keyName){
        return jedis.zcard(keyName);
    }


}
