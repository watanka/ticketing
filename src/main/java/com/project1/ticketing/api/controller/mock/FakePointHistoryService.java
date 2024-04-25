package com.project1.ticketing.api.controller.mock;

import com.project1.ticketing.api.dto.request.PointRequest;
import com.project1.ticketing.api.dto.response.PointHistoryResponse;
import com.project1.ticketing.domain.point.components.IPointHistoryService;
import com.project1.ticketing.domain.point.models.PointType;

import java.util.List;

//@Service
public class FakePointHistoryService implements IPointHistoryService {
    @Override
    public PointHistoryResponse updatePoint(PointRequest request) {

        long userId = request.getUserId();
        long amount = request.getAmount();
        PointType pointType = request.getPointType();

        if (userId==0L && pointType == PointType.USE){ // 실패 케이스
            throw new RuntimeException("잔액이 부족합니다.");
        }else{
            return PointHistoryResponse.builder()
                    .userId(userId)
                    .amount(amount)
                    .pointType(pointType.toString())
                    .build();
        }
    }

    @Override
    public List<PointHistoryResponse> getAllPointHistory(long userId) {
        return List.of(
                PointHistoryResponse.builder()
                        .userId(userId)
                        .amount(70000L)
                        .pointType(PointType.USE.toString())
                        .build(),
                PointHistoryResponse.builder()
                        .userId(userId)
                        .amount(50000L)
                        .pointType(PointType.CHARGE.toString())
                        .build()
                );
    }


    @Override
    public PointHistoryResponse checkBalance(long userId) {
        if (userId == 0L){
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }
        return PointHistoryResponse.builder()
                .userId(userId)
                .amount(800000L)
                .build();
    }
}
