package com.project1.ticketing.domain.point.components;

import com.project1.ticketing.domain.point.models.PointType;

public class PointValidator {
    public void validate(long balance, long amount, PointType pointType){

        if (pointType == PointType.USE && balance < amount){
            throw new RuntimeException("잔액이 부족합니다.");
        }

    }

}
