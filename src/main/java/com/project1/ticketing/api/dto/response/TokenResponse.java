package com.project1.ticketing.api.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponse {

    private String uuid;
    private String token;
    private long waitingNum;

    public TokenResponse(String uuid, String token, long waitingNum) {
        this.uuid = uuid;
        this.token = token;
        this.waitingNum = waitingNum;
    }
}
