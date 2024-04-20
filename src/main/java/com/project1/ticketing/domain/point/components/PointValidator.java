package com.project1.ticketing.domain.point.components;

import com.project1.ticketing.api.controller.PointController;
import com.project1.ticketing.domain.point.models.PointType;
import com.project1.ticketing.domain.point.models.User;
import com.project1.ticketing.domain.point.repository.PointCoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PointValidator {

    PointCoreRepository pointRepository;

    @Autowired
    public PointValidator(PointCoreRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public User validateUser(long userId){
        return pointRepository.getUser(userId).orElseThrow(
                ()->new RuntimeException("사용자를 찾지 못하였습니다."));
    }

    public void validatePoint(long balance, long amount, PointType pointType){
        if (pointType == PointType.USE && balance < amount){
            throw new RuntimeException("잔액이 부족합니다.");
        }

    }

}
