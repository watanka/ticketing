package com.project1.ticketing.domain.point.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
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
    public Point(User user, long amount, PointType pointType) {
        this.pointType = pointType;
        this.user = user;
        this.amount = amount;
    }


}
