package com.project1.ticketing.domain.point.infrastructure;

import com.project1.ticketing.api.dto.response.PointHistoryResponse;
import com.project1.ticketing.domain.point.models.PointHistory;
import com.project1.ticketing.domain.point.models.PointType;
import com.project1.ticketing.domain.point.models.User;
import com.project1.ticketing.domain.point.repository.PointCoreRepository;
import com.project1.ticketing.domain.point.repository.PointHistoryJpaRepository;
import com.project1.ticketing.domain.point.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PointCoreRepositoryImpl implements PointCoreRepository {

    UserJpaRepository userRepository;
    PointHistoryJpaRepository pointHistoryRepository;

    public PointCoreRepositoryImpl(UserJpaRepository userRepository, PointHistoryJpaRepository pointHistoryRepository) {
        this.userRepository = userRepository;
        this.pointHistoryRepository = pointHistoryRepository;
    }

    public void deleteAll(){
        userRepository.deleteAll();
        pointHistoryRepository.deleteAll();
    }

    @Override
    public Optional<User> getUser(long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<PointHistory> getAllPointHistoryByUserId(long userId) {
        return pointHistoryRepository.findAllByUserId(userId);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public PointHistory savePointHistory(PointHistory pointHistory) {
        return pointHistoryRepository.save(pointHistory);
    }
}
