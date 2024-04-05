package com.project1.ticketing.api.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointRequestDTO {
    // user_id
    // amount

    private long userId;
    private long amount;

    public PointRequestDTO(long userId, long amount) {
        this.userId = userId;
        this.amount = amount;
    }
}
