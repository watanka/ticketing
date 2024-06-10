package com.project1.ticketing.domain.token.components;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class TokenScheduler {


//    private final TokenService tokenService;
//
//    @Scheduled(fixedDelay = 1 * 60 * 1000)
//    public void expireTokens(){
//        System.out.println("======================");
//        System.out.println("token monitoring...");
//        long numActivateTokens = tokenService.activate();
//        long numWatkingTokens = tokenService.updateWaitingNum();
//
//        System.out.println("# activated tokens: "+ numActivateTokens + "\n# waiting tokens: " + numWatkingTokens);
//        System.out.println("======================");
//    }
}


