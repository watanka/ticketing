package com.project1.ticketing.domain.token.components;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class JedisService {


    private final String url;


    private final int port;

    JedisPool pool;

    public JedisService(@Value("${spring.redis.url}") String url,
                        @Value("${spring.redis.port}") int port, JedisPool pool) {

        this.url = url;
        this.port = port;
        this.pool = new JedisPool(url, port);

    }


}
