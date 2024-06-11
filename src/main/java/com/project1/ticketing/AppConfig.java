package com.project1.ticketing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.ConnectionPoolConfig;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class AppConfig {

    @Bean
    public ConnectionPoolConfig jedisPoolConfig(){
        ConnectionPoolConfig jedisPoolConfig = new ConnectionPoolConfig();

        jedisPoolConfig.setMaxTotal(100);
        jedisPoolConfig.setMaxIdle(50);

        return jedisPoolConfig;
    }
}
