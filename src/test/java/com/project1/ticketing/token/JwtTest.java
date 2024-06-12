package com.project1.ticketing.token;

import com.project1.ticketing.api.utils.exceptions.InvalidTokenException;
import com.project1.ticketing.domain.token.components.JwtProvider;
import com.project1.ticketing.domain.token.components.TokenService;
import com.project1.ticketing.domain.token.repository.ITokenRepository;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class JwtTest {

    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private ITokenRepository tokenRepository;

    @Autowired
    private TokenService tokenService;

    @Value("${spring.jwt.access-expiration}")
    Long accessExpiration;

    @Value("${service.jwt.secret-key}")
    String secretKey;



    @Test
    @DisplayName("Jwt 토큰을 생성하고, userId가 잘 들어가 있는지 확인한다.")
    void 토큰_생성후_정보확인(){
        long userId = 1;
        Date issuedAt = new Date();
        String token = jwtProvider.create(userId, issuedAt);

        Claims claims = jwtProvider.getClaims(token);

        assertThat(claims.get("userId", String.class)).isEqualTo(String.valueOf(userId));


    }

    @Test
    @DisplayName("토큰 유효성 검증 - 올바른 토큰인 경우")
    void 토큰_유효성_검증_happyCase(){
        long userId = 1;
        Date issuedAt = new Date();
        String validToken = jwtProvider.create(userId, issuedAt);

        assertDoesNotThrow(() -> {
            jwtProvider.verifyToken(validToken);
        });
    }


    @Test
    @DisplayName("토큰 유효성 검증 - secret-key가 다를 경우")
    void 토큰_유효성_검증_sadCase(){
        String fakeToken = "I-HAVE-A-FAKE-TOKEN";
        assertThrows(InvalidTokenException.class, ()-> jwtProvider.verifyToken(fakeToken));
    }

    @Test
    @DisplayName("토큰 발급 요청")
    void 토큰_발급요청후_조회(){
        long userId = 1L;
        String token = tokenService.register(userId);

        long waitingNum = tokenService.checkWaitingNum(token);

        assertThat(waitingNum).isEqualTo(1);

    }


    @Test
    @DisplayName("토큰 대기열 순서 조회")
    void 대기열_순서를_확인한다(){

    }

}
