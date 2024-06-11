package com.project1.ticketing.api.dto.response;


import com.project1.ticketing.domain.token.models.TokenStatus;
import lombok.Builder;


@Builder
public record TokenResponse(long userId, long tokenId, long waitingNum, TokenStatus status){

//    public static TokenResponse from(Token token){
//        return TokenResponse.builder()
//                .userId(token.getUserId())
//                .tokenId(token.getTokenId())
//                .waitingNum(token.getWaitingNum())
//                .status(token.getStatus())
//                .build();
//    }

}
