package com.project1.ticketing.domain.token.components;

import com.project1.ticketing.api.dto.response.TokenResponse;
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


    public TokenResponse queue(long userId){
        return null;
    }


    public List<Token> getByStatus(TokenStatus tokenStatus){
        return null;
    }

    public void activate(){
        List<Token> tokensToActivate = tokenRepository.findByStatus(TokenStatus.WAIT);
    }


    // 입장할 값




}
