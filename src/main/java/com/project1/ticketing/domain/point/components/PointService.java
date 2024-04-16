package com.project1.ticketing.domain.point.components;

import com.project1.ticketing.api.dto.request.PointRequest;
import com.project1.ticketing.api.dto.response.PointResponse;
import com.project1.ticketing.domain.point.models.Point;
import com.project1.ticketing.domain.point.models.PointType;
import com.project1.ticketing.domain.point.models.User;
import com.project1.ticketing.domain.point.repository.IPointRepository;
import com.project1.ticketing.domain.point.repository.IUserRepository;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
public class PointService{

    PointValidator pointValidator;
    IPointRepository pointRepository;
    IUserRepository userRepository;

    public PointService(PointValidator pointValidator, IPointRepository pointRepository, IUserRepository userRepository) {
        this.pointValidator = pointValidator;
        this.pointRepository = pointRepository;
        this.userRepository = userRepository;
    }

    public Point updatePoint(long userId, long amount, PointType pointType) {
        // 유저를 찾는다.
        User user = userRepository.findById(userId).get();
        long balance = user.getBalance();

        pointValidator.validate(balance, amount);


        // 유저
        if (pointType == PointType.USE){
            user.usePoint(amount);
        }else{
            user.chargePoint(amount);
        }
        Point point = new Point(user, amount, pointType);

        userRepository.save(user);
        pointRepository.save(point);
        return point;

    }
    public long checkPoint(long userId) {
        User user = userRepository.findById(userId).get();
        return user.getBalance();

    }
}
