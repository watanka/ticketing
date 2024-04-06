package com.project1.ticketing;

import org.springframework.stereotype.Service;

public class Token{
    private String uuid;
    private TokenStatus status;
    private long waitingNum;


    public Token(String uuid){
        this.uuid = uuid;
        this.status = TokenStatus.WAIT;
    }

    public Token(String uuid, TokenStatus status, long waitingNum){
        this.uuid = uuid;
        this.status = status;
        this.waitingNum = waitingNum;
    }

    public TokenStatus getStatus(){
        return status;
    }

    public String getUUID(){
        return uuid;
    }

    public void setStatus(TokenStatus status){
        this.status = status;
    }

    public long getWaitingNum(){
        return waitingNum;
    }

    public void setWaitingNum(long waitingNum){
        this.waitingNum = waitingNum;
    }
}
