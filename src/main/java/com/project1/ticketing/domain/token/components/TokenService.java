package com.project1.ticketing.domain.token.components;

import com.project1.ticketing.api.dto.response.TokenResponse;
import com.project1.ticketing.domain.token.models.Token;
import com.project1.ticketing.domain.token.models.TokenStatus;
import com.project1.ticketing.domain.token.repository.ITokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final ITokenRepository tokenRepository;


    public TokenResponse queue(long userId){
        List<Token> tokenWaiting = tokenRepository.findByStatus(TokenStatus.WAIT);
        long waitingNum = tokenWaiting.size() + 1; //이미 기다리고 있는 줄 바로 뒤에 세움

        // Token 생성
        Token newToken = Token.builder()
                .userId(userId)
                .isExpired(false)
                .expiredAt(ZonedDateTime.now())
                .waitingNum(waitingNum)
                .build();

        return TokenResponse.from(newToken);
    }


    public List<Token> getByStatus(TokenStatus tokenStatus){
        return null;
    }

    public void activate() {

        int ACTIVATE_NUM = 50;
        List<Token> tokensToActivate = tokenRepository.findByStatus(TokenStatus.WAIT);
        tokensToActivate.sort(Comparator.comparing(Token::getExpiredAt));
        tokensToActivate.subList(0, ACTIVATE_NUM);

        for (Token token : tokensToActivate) {
            token.activate();
            tokenRepository.save(token);

        }

    }

    public void updateWaitingNum(){
        List<Token> tokensToUpdateWaitingNum = tokenRepository.findByStatus(TokenStatus.WAIT);
        tokensToUpdateWaitingNum.sort(Comparator.comparing(Token::getExpiredAt));

        int newWaitingNum = 1;
        for (Token token : tokensToUpdateWaitingNum) {
            token.updateWaitingNum(newWaitingNum++);
            tokenRepository.save(token);
        }

        // 대기중인 토큰들의 waitingNum을 업데이트해줌
    }



    public TokenResponse checkWaitingNum(long userId){
        // 조회할때마다 waitingNum 업데이트해줘야함
        Token token = tokenRepository.findByUserId(userId);
        // TODO: exception
        return TokenResponse.from(token);

    }


}
