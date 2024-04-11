package com.project1.ticketing.domain.token.models;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Token{
    private String uuid;
    private String tokenId;
    private TokenStatus status;
    private long waitingNum;


    public Token(String uuid){
        this.uuid = uuid;
        this.status = TokenStatus.WAIT;
    }

    public Token(String uuid, String tokenId, long waitingNum) {
        this.uuid = uuid;
        this.tokenId = tokenId;
        this.waitingNum = waitingNum;
    }

    public Token(String uuid, long waitingNum){
        this.uuid = uuid;
        this.waitingNum = waitingNum;
    }

    public Token(String uuid, TokenStatus status, long waitingNum){
        this.uuid = uuid;
        this.status = status;
        this.waitingNum = waitingNum;
    }

}
