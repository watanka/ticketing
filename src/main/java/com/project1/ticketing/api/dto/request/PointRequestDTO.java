package com.project1.ticketing.api.dto.request;


import com.project1.ticketing.domain.point.models.PointType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointRequestDTO {
    // user_id
    // amount

    private long userId;
    private PointType pointType;
    private long amount;

    public PointRequestDTO(long userId, long amount, PointType pointType) {
        this.userId = userId;
        this.amount = amount;
        this.pointType = pointType;
    }
}
