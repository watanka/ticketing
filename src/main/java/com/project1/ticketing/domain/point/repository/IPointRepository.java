package com.project1.ticketing.domain.point.repository;

import com.project1.ticketing.domain.point.models.Point;

import java.util.List;

public interface IPointRepository {
    void save(Point point);

    List<Point> findById(long userId);

}
