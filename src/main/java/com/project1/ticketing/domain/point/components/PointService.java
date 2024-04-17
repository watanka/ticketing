package com.project1.ticketing.domain.point.components;

import com.project1.ticketing.api.dto.request.PointRequest;
import com.project1.ticketing.api.dto.response.PointResponse;
import com.project1.ticketing.domain.point.models.Point;
import com.project1.ticketing.domain.point.models.PointType;
import com.project1.ticketing.domain.point.models.User;
import com.project1.ticketing.domain.point.repository.IPointRepository;
import com.project1.ticketing.domain.point.repository.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class PointService{

    IUserRepository userRepository;
    IPointRepository pointRepository;
    PointValidator pointValidator;


    public PointService(IUserRepository userRepository, IPointRepository pointRepository, PointValidator pointValidator) {
        this.userRepository = userRepository;
        this.pointRepository = pointRepository;
        this.pointValidator = pointValidator;
    }



    public Point updatePoint(long userId, long amount, PointType pointType) {

        // user 포인트 조회
        User user = userRepository.getById(userId);
        long balance = user.getBalance();

        pointValidator.validate(balance, amount, pointType);

        Point point = new Point(user, amount, pointType);
        pointRepository.save(point);

        if (pointType==PointType.USE){
            user.usePoint(amount);
        }else{
            user.chargePoint(amount);
        }
        userRepository.update(user);

        return point;
    }

    public long checkPoint(long userId) {
        User user = userRepository.getById(userId);
        return user.getBalance();
    }
}
