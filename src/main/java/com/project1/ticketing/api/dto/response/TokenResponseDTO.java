package com.project1.ticketing.api.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponseDTO {

    private long userId;
    private String token;
    private long waitingNum;

    public TokenResponseDTO(long userId, String token, long waitingNum) {
        this.userId = userId;
        this.token = token;
        this.waitingNum = waitingNum;
    }
}
