package com.project1.ticketing.domain.point.components;

public class PointValidator {

    public void validate(long balance, long amount){
        if (balance < amount){
            throw new RuntimeException("잔액이 부족합니다.");
        }
    }
}
