package com.project1.ticketing.domain.token.components;

import com.project1.ticketing.domain.token.models.Token;
import com.project1.ticketing.domain.token.models.TokenStatus;
import com.project1.ticketing.domain.token.repository.ITokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final ITokenRepository tokenRepository;


    public Token queue(long userId){

        Token newToken = Token.builder()
                .tokenId()
                            .userId(userId)
                            .
        return tokenRepository.save(token);

    }


    public List<Token> getByStatus(TokenStatus tokenStatus){
        return waitingLine.stream()
                .filter(token -> token.getStatus() == tokenStatus)
                .collect(Collectors.toList());
    }

    public void activate(){
        tokenRepository.findByStatus
    }


    public long countActiveToken(){
        System.out.println("counting");
        return waitingLine.stream()
                .filter(token ->token.getStatus() == TokenStatus.ACTIVE)
                .count();
    }

    // 입장할 값




}
