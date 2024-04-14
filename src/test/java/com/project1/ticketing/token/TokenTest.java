package com.project1.ticketing.token;

import com.project1.ticketing.Token;
import com.project1.ticketing.TokenManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.when;

public class TokenTest {
    TokenManager tokenManager;

    @BeforeEach
    void setup(){
        List<Token> waitingLine = Mockito.mock(List.class);
        tokenManager = new TokenManager(50, waitingLine);

    }

    @Test
    @DisplayName("활성화 토큰 수 체크")
    void 활성화된_토큰_갯수를_체크한다(){

        long numCurrentActivatedToken = 10L;

        when().thenReturn(numCurrentActivatedToken);



    }

    @Test
    @DisplayName("#maxActiveToken>#curActiveToken일 경우, 대기열 앞부터 활성화")
    void 토큰_활성화_조건_테스트(){

    }

    @Test
    @DisplayName("최대 활성시간이 지난 토큰은 만료시킴")
    void 토큰_만료_테스트(){

    }

}
