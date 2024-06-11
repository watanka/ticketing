package com.project1.ticketing.domain.token.components;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class TokenScheduler {


    private final TokenService tokenService;
    private final Long numActivationPerMin;

    public TokenScheduler(TokenService tokenService,
                          @Value("${spring.queue.num-activation-per-min}") Long numActivationPerMin) {
        this.tokenService = tokenService;
        this.numActivationPerMin = numActivationPerMin;
    }

    @Scheduled(fixedDelay = 1 * 60 * 1000)
    public void expireTokens(){
        System.out.println("======================");
        System.out.println("token monitoring...");
        tokenService.activateTokens(numActivationPerMin);
        tokenService.expireTokens();

//        System.out.println("# activated tokens: "+ numActivateTokens + "\n# waiting tokens: " + numWatkingTokens);
        System.out.println("======================");
    }
}


