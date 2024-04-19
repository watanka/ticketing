package com.project1.ticketing.api.dto.response;

import com.project1.ticketing.domain.point.models.PointHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class PointHistoryResponse {

    private long userId;
    private long amount;
    private String pointType;

    public PointHistoryResponse(long userId, long amount, String pointType) { // TODO: 유저의 balance를 조회할 때도 같은 DTO를 사용하니 어색하다. balance 조회할 때, pointType에는 어떤값이 들어가야하나.
        this.userId = userId;
        this.amount = amount;
        this.pointType = pointType;
    }

    public static PointHistoryResponse from(PointHistory pointHistory){
        return PointHistoryResponse.builder()
                .userId(pointHistory.getUserId())
                .amount(pointHistory.getAmount())
                .pointType(pointHistory.getPointType().toString())
                .build();

    }


}
