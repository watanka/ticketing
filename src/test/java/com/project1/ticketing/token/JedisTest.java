package com.project1.ticketing.token;

import com.project1.ticketing.domain.token.repository.TokenJedisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JedisTest {

    @Autowired
    TokenJedisRepository tokenRepository;

    String testingQueue = "testing_queue";

    @BeforeEach
    public void setUp(){
        tokenRepository.removeAll();
    }

    void insertToken(String keyName, String token, long userId, long time){
        tokenRepository.insert(keyName, token, time);
    }

    @Test
    @DisplayName("대기열에 없는 토큰을 조회한다.")
    void 대기열에_없는_토큰_조회(){
        String token = "token-not-in-queue";

        boolean isRegistered = tokenRepository.checkTokenInQueue(testingQueue, token);

        assertThat(isRegistered).isFalse();
    }


    @Test
    @DisplayName("토큰의 대기열 순서를 조회한다.")
    void 토큰의_대기열_순서를_조회한다(){

    }

    @Test
    @DisplayName("토큰을 대기열에 등록한다.")
    void 토큰을_대기열에_등록한다(){
        long userId = 1L;
        insertToken(testingQueue, "fake-token", userId, System.currentTimeMillis());

        assertThat(tokenRepository.checkTokenInQueue(testingQueue, "fake-token")).isTrue();

    }

    @Test
    @DisplayName("먼저 들어온 토큰이 대기열 앞에 위치한다.")
    void 먼저_들어온_토큰이_대기열_앞에_위치한다(){
        long userId = 1L;

        String nowToken = "fake-token-now";
        String lateToken = "fake-token-late";
        String earlyToken = "fake-token-early";

        long now = System.currentTimeMillis();
        long lateTime = now + 10000;
        long earlyTime = now - 10000;

        insertToken(testingQueue, nowToken, userId, now);
        insertToken(testingQueue, lateToken, userId, lateTime);
        insertToken(testingQueue, earlyToken, userId, earlyTime);


        assertThat(tokenRepository.getTokenInRange(testingQueue, 0, -1))
                .isEqualTo(List.of(earlyToken, nowToken, lateToken)); // 시간 순서대로
    }

    @Test
    @DisplayName("토큰을 대기열에서 제거한다.")
    void 토큰을_대기열에서_제거한다(){
        long userId = 1L;
        String token = "fake-token";
        insertToken(testingQueue, token, userId, System.currentTimeMillis());

        tokenRepository.remove(testingQueue, token);

        assertThat(tokenRepository.checkTokenInQueue(testingQueue, token)).isFalse();
        assertThat(tokenRepository.getTotalLength(testingQueue)).isZero();


    }

    @Test
    @DisplayName("지정한 수만큼 토큰을 대기열에서 제거한다.")
    void 지정_수만큼_대기열에서_토큰을_제거한다(){
        long userId = 1L;


        //given: 대기열에 5개의 토큰
        for (int i=0; i<4; i++) {
            insertToken(testingQueue, "token-"+String.valueOf(i+1), userId, System.currentTimeMillis());
        }
        insertToken(testingQueue, "late-token", userId, System.currentTimeMillis() + 100);

        //when: 4개의 토큰 대기열에서 제거
        tokenRepository.removeTokensInRange(testingQueue, 0, 3);

        //then
        assertThat(tokenRepository.getTotalLength(testingQueue)).isOne();
        assertThat(tokenRepository.checkTokenInQueue(testingQueue,"late-token")).isTrue();

    }


}
