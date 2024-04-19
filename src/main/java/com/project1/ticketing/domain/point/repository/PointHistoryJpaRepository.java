package com.project1.ticketing.domain.point.repository;

import com.project1.ticketing.domain.point.models.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointHistoryJpaRepository extends JpaRepository<PointHistory, Long> {

    List<PointHistory> findAllByUserId(long userId);
}
