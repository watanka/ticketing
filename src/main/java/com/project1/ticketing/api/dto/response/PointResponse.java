package com.project1.ticketing.api.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointResponse {

    private long userId;
    private long amount;

    public PointResponse(long userId, long amount) {
        this.userId = userId;
        this.amount = amount;
    }
}
