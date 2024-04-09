package com.project1.ticketing.api.dto.response;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TokenResponseDTO {

    private String uuid;
    private String token;
    private long waitingNum;

    public TokenResponseDTO(String uuid, String token, long waitingNum) {
        this.uuid = uuid;
        this.token = token;
        this.waitingNum = waitingNum;
    }
}
