package com.project1.ticketing.token;

import com.project1.ticketing.domain.token.components.JwtProvider;
import com.project1.ticketing.domain.token.models.Token;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TokenTest {


    @Autowired
    private JwtProvider jwtProvider;


    @Test
    @DisplayName("Jwt 토큰을 생성한다.")
    void 토큰_생성_확인(){
        long userId = 1;

        String token = jwtProvider.create(userId, new Date());

        System.out.println(token);




    }


    @Test
    @DisplayName("토큰의 대기열 순서 조회")
    void check_token_order_in_waitingline(){

    }


    @Test
    @DisplayName("토큰 대기열 순서 조회")
    void 대기열_순서를_확인한다(){

    }

}
