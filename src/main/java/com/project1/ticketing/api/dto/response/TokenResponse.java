package com.project1.ticketing.api.dto.response;


import com.project1.ticketing.domain.token.models.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TokenResponse {

    private long userId;
    private long tokenId;
    private long waitingNum;

    public static TokenResponse from(Token token){
        return TokenResponse.builder()
                .userId(token.getUserId())
                .tokenId(token.getTokenId())
                .waitingNum(token.getWaitingNum())
                .build();
    }

}
