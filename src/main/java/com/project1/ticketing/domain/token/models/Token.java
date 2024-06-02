package com.project1.ticketing.domain.token.models;


import com.project1.ticketing.domain.token.components.TokenService;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;


@Entity
@Getter
@Setter
@ToString
public class Token{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tokenId;
    private long userId;
    private ZonedDateTime expiredAt;
    private TokenStatus status;
    private long waitingNum;

    @Builder
    public Token(long waitingNum, long userId, ZonedDateTime expiredAt, TokenStatus status) {
        this.waitingNum = waitingNum;
        this.userId = userId;
        this.expiredAt = expiredAt;
        this.status = status;
    }

    protected Token() {
    }

    public void expire(){
        this.status = TokenStatus.DONE;
    }

    public void queue(){
        this.status = TokenStatus.WAIT;
    }

    public void activate(){
        this.status = TokenStatus.ACTIVE;
        this.expiredAt = ZonedDateTime.now().plusMinutes(5); // TODO: 상수로 변경
    }

    public void updateWaitingNum(long waitingNum){
        this.waitingNum = waitingNum;
    }

}
