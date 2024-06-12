package com.project1.ticketing.token;


import com.project1.ticketing.domain.token.components.TokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


@SpringBootTest
public class TokenServiceTest {
    @Autowired
    TokenService tokenService;

    @BeforeEach
    public void setUp(){
        tokenService.removeAll();
    }


    @Test
    @DisplayName("토큰을 발급받는다.")
    void 토큰을_발급받는다() throws InterruptedException {
        long userId = 1L;
        long numRegisterBeforeSUT = 150;

        for (int i=0;i<numRegisterBeforeSUT;i++) {
            tokenService.register(i);
        }

        Thread.sleep(10);

        String token = tokenService.register(userId);

        assertDoesNotThrow(()-> tokenService.validateToken(token));
        assertThat(tokenService.checkWaitingNum(token)).isEqualTo(numRegisterBeforeSUT);


    }


    @Test
    @DisplayName("토큰을 활성화시킨다.")
    void 대기열_토큰활성화_시_활성화토큰리스트로_이동(){
        // given
        long userId = 1L;
        long numInWaitingQueue = 150;
        long numActivation = 100;

        for (int i=0;i<numInWaitingQueue;i++) {
            tokenService.register(i);
        }

        // when
        tokenService.activateTokens(numActivation);

        // then
        assertThat(tokenService.getWaitingQueueLength()).isEqualTo(50);
        assertThat(tokenService.getActivationQueueLength()).isEqualTo(100);


    }

}
