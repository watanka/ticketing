package com.project1.ticketing.domain.point.repository;

import com.project1.ticketing.domain.point.models.PointHistory;
import com.project1.ticketing.domain.point.models.User;

import java.util.List;
import java.util.Optional;

public interface PointCoreRepository {
    // 조회

    User getUser(long userId);
    List<PointHistory> getAllPointHistoryByUserId(long userId);

    // 쓰기
    User saveUser(User user);

    PointHistory savePointHistory(PointHistory pointHistory);

}
