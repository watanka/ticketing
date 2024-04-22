package com.project1.ticketing.domain.point.repository;

import com.project1.ticketing.domain.point.models.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointHistoryJpaRepository extends JpaRepository<PointHistory, Long> {

    List<PointHistory> findAllByUserId(long userId);
    void deleteAll();

    PointHistory save(PointHistory pointHistory);
}
