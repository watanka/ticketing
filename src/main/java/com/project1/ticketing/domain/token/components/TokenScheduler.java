package com.project1.ticketing.domain.token.components;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@RequiredArgsConstructor
@Component
public class TokenScheduler {


    private final RedisZSetService redisZSetService;


    @Scheduled(cron = "0 * * * * *")
    public void expireTokens(){
    }
}
