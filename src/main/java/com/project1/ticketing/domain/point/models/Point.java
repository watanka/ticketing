package com.project1.ticketing.domain.point.models;

import jakarta.persistence.*;
import lombok.Builder;

public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private PointType pointType;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private long amount;

    @Builder
    public Point(Long id, PointType pointType, User user, long amount) {
        this.id = id;
        this.pointType = pointType;
        this.user = user;
        this.amount = amount;
    }
}
