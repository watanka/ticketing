package com.project1.ticketing.domain.point.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class PointHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private PointType pointType;


    private long userId;

    private long amount;

    @Builder
    public PointHistory(long userId, long amount, PointType pointType) {
        this.userId = userId;
        this.amount = amount;
        this.pointType = pointType;
    }


}
