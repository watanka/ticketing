package com.project1.ticketing.domain.point.components;

import com.project1.ticketing.api.dto.request.PointRequest;
import com.project1.ticketing.api.dto.response.PointHistoryResponse;
import com.project1.ticketing.domain.point.models.PointHistory;
import com.project1.ticketing.domain.point.models.PointType;
import com.project1.ticketing.domain.point.models.User;
import com.project1.ticketing.domain.point.repository.PointCoreRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class PointHistoryService implements IPointHistoryService {
    PointCoreRepository pointRepository;
    PointValidator pointValidator;


    @Autowired
    public PointHistoryService(PointCoreRepository pointRepository, PointValidator pointValidator) {
        this.pointRepository = pointRepository;
        this.pointValidator = pointValidator;
    }


    @Override
    public PointHistoryResponse updatePoint(PointRequest request) {
        long userId = request.getUserId();
        long amount = request.getAmount();
        PointType pointType = request.getPointType();

        // TODO: validator가 조회한 유저를 리턴하는 건 두 개의 책임을 갖게되는 것 같아서 문제이지만, 코드가 좀더 깔끔해보이는 것가탇.
        User foundUser = pointValidator.validateUser(userId);
        long balance = foundUser.getBalance();

        pointValidator.validatePoint(balance, amount, pointType);

        PointHistory pointHistory = new PointHistory(userId, amount, pointType);
        pointRepository.savePointHistory(pointHistory);

        foundUser.usePoint(amount);
        pointRepository.saveUser(foundUser);

        return PointHistoryResponse.from(pointHistory);


    }

    @Override
    public List<PointHistoryResponse> getAllPointHistory(long userId) {
        pointValidator.validateUser(userId);
        List<PointHistory> pointHistoryList = pointRepository.getAllPointHistoryByUserId(userId);

        return pointHistoryList.stream()
                .map(PointHistoryResponse::from)
                .collect(Collectors.toList());

    }

    @Override
    public PointHistoryResponse checkBalance(long userId){
        // userId로 user 조회 => userRepository.findById => pointCoreRepository.getUser
        // user 조회 유무 처리
        // user balance 조회 => userRepository.findById.getBalance => pointCoreRepository.checkBalance
        User foundUser = pointValidator.validateUser(userId);
        return PointHistoryResponse.builder()
                .userId(userId)
                .pointType(PointType.BALANCE.toString())
                .amount(foundUser.getBalance())
                .build();

    }
}
