package com.project1.ticketing.domain.token.components;

import com.project1.ticketing.api.utils.exceptions.InvalidTokenException;
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


    public long checkWaitingNum(String token){
        validateToken(token);
        long waitingNum = tokenRepository.getWaitingNum(token);

        return waitingNum;
    }
    public String register(long userId) {
        // TODO: 검증로직 추가
        String token = jwtProvider.create(userId, new Date());
        tokenRepository.insert(token, userId);

        return token;


    }


    public void validateToken(String token) {
        // 토큰 대기열 조회
        String tokenFound = tokenRepository.findByToken(token);

        // 토큰 유효성 검증
        jwtProvider.verifyToken(tokenFound);
    }

    public List<String> activateTokens(long numActivation){
        List<String> userToActviateList = tokenRepository.getActivateUserList(numActivation);
        // 유저 리스트에게 jwt 토큰 부여
//        userToActviateList.stream().map(userId -> createToken(userId));
        return null;
    }

    public void expireTokens(){
        // [활성화 토큰리스트]에서 만료된 토큰 제거
    }

    public long getTimeLeft(long numWaitingAhead){
        return numWaitingAhead * 30;
    }
}

