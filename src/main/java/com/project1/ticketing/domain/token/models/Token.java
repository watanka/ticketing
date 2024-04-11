package com.project1.ticketing.domain.token.models;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Token{
    private String uuid;
    private long concertId;
    private String tokenId;
    private TokenStatus status;
    private long waitingNum;


    public Token(long concertId, String uuid, String tokenId){
        this.tokenId = tokenId;
        this.concertId = concertId;
        this.uuid = uuid;
        this.status = TokenStatus.WAIT;
    }

    public Token(long concertId, String uuid, String tokenId, long waitingNum) {
        this.concertId = concertId;
        this.uuid = uuid;
        this.tokenId = tokenId;
        this.waitingNum = waitingNum;
    }

    public Token(long concertId, String uuid, long waitingNum){
        this.concertId = concertId;
        this.uuid = uuid;
        this.waitingNum = waitingNum;
    }

    public Token(long concertId, String uuid, TokenStatus status, long waitingNum){
        this.concertId = concertId;
        this.uuid = uuid;
        this.status = status;
        this.waitingNum = waitingNum;
    }

}
