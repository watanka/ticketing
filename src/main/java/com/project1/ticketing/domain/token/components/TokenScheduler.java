package com.project1.ticketing.domain.token.components;

import com.project1.ticketing.domain.token.repository.ITokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@RequiredArgsConstructor
@Component
public class TokenScheduler {


    private final TokenService tokenService;

    @Scheduled(cron = "* 1 * * * *")
    public void expireTokens(){
        tokenService.activate();
        tokenService.updateWaitingNum();
    }
}
