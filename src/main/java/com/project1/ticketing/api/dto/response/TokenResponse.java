package com.project1.ticketing.api.dto.response;


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


}
