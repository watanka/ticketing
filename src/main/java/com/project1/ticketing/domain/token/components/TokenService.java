package com.project1.ticketing.domain.token.components;

import com.project1.ticketing.api.dto.response.TokenResponse;
import com.project1.ticketing.domain.token.models.Token;
import com.project1.ticketing.domain.token.models.TokenStatus;
import com.project1.ticketing.domain.token.repository.ITokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.min;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtProvider jwtProvider;
    private final ITokenRepository tokenRepository;


    public JwtPayload queue(String token){
        // TODO: 검증로직 추가
        // 존재하는 유저인지
        // 이미 토큰을 발급받지는 않았는지?
        long waitingNum = 0;

        List<Token> tokenWaiting = tokenRepository.findByStatus(TokenStatus.WAIT);
         //이미 기다리고 있는 줄 바로 뒤에 세움
        if (tokenWaiting == null){
            System.out.println("No Waiting Found.");

        }else{
            waitingNum = tokenWaiting.size() + 1;
        }

        // 새로운 토큰 발급
        JwtPayload jwtPayload = new JwtPayload();

        // 새로 발급한 토큰 등록
        tokenRepository.save(newToken);

        return jwtPayload;
    }


    public List<Token> getByStatus(TokenStatus tokenStatus){
        return null;
    }

    public long activate(){

        int ACTIVATE_NUM = 50;

        List<Token> tokensToActivate = tokenRepository.findByStatus(TokenStatus.WAIT);
        if (tokensToActivate == null){
            System.out.println("skip since no tokens waiting.");
            return 0;
        }
        tokensToActivate.sort(Comparator.comparing(Token::getExpiredAt));
        tokensToActivate = tokensToActivate.subList(0, Math.min(ACTIVATE_NUM, tokensToActivate.size()));

        for (Token token : tokensToActivate) {
            token.activate();
            tokenRepository.save(token);
        }

        return tokensToActivate.size();



    }

    public long updateWaitingNum(){

        List<Token> tokensToUpdateWaitingNum = tokenRepository.findByStatus(TokenStatus.WAIT);
        if (tokensToUpdateWaitingNum.size() == 0){
            System.out.println("No Token waiting.");
            return 0;
        }
        tokensToUpdateWaitingNum.sort(Comparator.comparing(Token::getExpiredAt));


        int newWaitingNum = 1;
        for (Token token : tokensToUpdateWaitingNum) {
            token.updateWaitingNum(newWaitingNum++);
            tokenRepository.save(token);
        }
        return newWaitingNum;
        // 대기중인 토큰들의 waitingNum을 업데이트해줌
    }



    public TokenResponse checkWaitingNum(long userId){
        // 조회할때마다 waitingNum 업데이트해줘야함
        Token token = tokenRepository.findByUserId(userId);
        if (token == null){
            throw new RuntimeException("유저 ID {" + userId + "}의 토큰을 찾을 수 없습니다.");
        }
        // TODO: exception
        return TokenResponse.from(token);

    }


}
