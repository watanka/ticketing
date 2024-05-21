package com.project1.ticketing.domain.token.models;


import jakarta.persistence.Entity;
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


    private long tokenId;
    private long userId;
    private boolean isExpired;
    private ZonedDateTime expiredAt;
    private TokenStatus status;
    private long waitingNum;

    @Builder
    public Token(long tokenId, long userId, boolean isExpired) {
        this.tokenId = tokenId;
        this.userId = userId;
        this.isExpired = isExpired;
    }

    public void expire(){
        this.status = TokenStatus.DONE;
        this.isExpired = true;
    }

    public void queue(){
        this.status = TokenStatus.WAIT;
    }

    public void activate(){
        this.status = TokenStatus.ACTIVE;
        this.expiredAt = ZonedDateTime.now().plusMinutes(5); // TODO: 상수로 변경
    }

}
