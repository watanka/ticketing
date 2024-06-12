package com.project1.ticketing.domain.token.components;

import com.project1.ticketing.api.utils.exceptions.InvalidTokenException;
import com.project1.ticketing.api.utils.response.BaseResponseStatus;
import com.project1.ticketing.domain.token.models.Token;
import com.project1.ticketing.domain.token.repository.ITokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static java.util.Collections.min;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtProvider jwtProvider;
    private final ITokenRepository tokenRepository;

    private final String waitingTokenQueue = "waiting-queue";
    private final String activatedTokenQueue = "activated-queue";



    public long checkWaitingNum(String token){
        validateToken(token);
        return tokenRepository.getWaitingNum(waitingTokenQueue, token)+1;
    }


    public String register(long userId) {
        // TODO: 검증로직 추가
        String token = jwtProvider.create(userId, new Date());
        tokenRepository.insert(waitingTokenQueue, token, System.currentTimeMillis());

        return token;


    }


    public void validateToken(String token) {
        // 토큰 대기열 조회
        boolean tokenInWaitingQueue = tokenRepository.checkTokenInQueue(waitingTokenQueue, token);

        if (!tokenInWaitingQueue){
            throw new InvalidTokenException(BaseResponseStatus.FAIL);
        }
        // 토큰 유효성 검증
        jwtProvider.verifyToken(token);
    }

    public List<String> activateTokens(long numActivation){
        List<String> activateList = tokenRepository.getTokenInRange(waitingTokenQueue, 0, numActivation-1);
        long removeLength = tokenRepository.removeTokensInRange(waitingTokenQueue, 0, numActivation-1);

        assert removeLength == activateList.size();

        // activateList를 activationTokenQueue로 이동
        // TODO: 만료시간 설정 필요
        for (String token : activateList) {
            tokenRepository.insert(activatedTokenQueue, token, System.currentTimeMillis());
        }

        return activateList;
    }

    public void expireTokens(){
        // [활성화 토큰리스트]에서 만료된 토큰 제거


    }

    public void removeAll(){
        tokenRepository.removeAll();
    }

    public long getTimeLeft(long numWaitingAhead){
        return numWaitingAhead * 30;
    }

    public long getWaitingQueueLength(){
        return tokenRepository.getTotalLength(waitingTokenQueue); // count from 0
    }

    public long getActivationQueueLength(){
        return tokenRepository.getTotalLength(activatedTokenQueue); // count from 0
    }
}

