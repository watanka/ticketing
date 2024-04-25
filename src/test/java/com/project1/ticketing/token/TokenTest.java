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


    }



    @Test
    @DisplayName("활성화 토큰 수 체크")
    void 활성화된_토큰_갯수를_체크한다(){

        long numCurrentActivatedToken = 10L;




    }

    @Test
    @DisplayName("토큰 활성화 상태 체크")
    void token_status(){

    }

    @Test
    @DisplayName("토큰의 대기열 순서 조회")
    void check_token_order_in_waitingline(){

    }

    @Test
    @DisplayName("#maxActiveToken>#curActiveToken일 경우, 대기열 앞부터 활성화")
    void activate_token(){

    }

    @Test
    @DisplayName("최대 활성시간이 지난 토큰은 만료시킴")
    void expire_token(){

    }

}
