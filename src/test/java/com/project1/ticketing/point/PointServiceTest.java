package com.project1.ticketing.point;

import com.project1.ticketing.domain.point.components.PointService;
import com.project1.ticketing.domain.point.components.PointValidator;
import com.project1.ticketing.domain.point.models.Point;
import com.project1.ticketing.domain.point.repository.IPointRepository;
import com.project1.ticketing.domain.point.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class PointServiceTest {

    @Mock
    PointValidator pointValidator;
    @Mock
    IPointRepository pointRepository;
    @Mock
    IUserRepository userRepository;
    @InjectMocks
    PointService pointService;

    @BeforeEach
    void setUp(){
        pointValidator = Mockito.mock(PointValidator.class);
        pointRepository = Mockito.mock(IPointRepository.class);
        userRepository = Mockito.mock(IUserRepository.class);

        pointService = new PointService(userRepository, pointRepository, pointValidator);

    }

    @Test
    @DisplayName("포인트를 조회한다.")
    void case0(){

        pointService.
    }

    @Test
    @DisplayName("포인트를 충전한다")
    void case1(){
        pointService.

    }

    @Test
    @DisplayName("포인트를 사용한다.")
    void case2(){

    }

    @Test
    @DisplayName("보유중인 포인트보다 많은 포인트 사용을 시도한다.")
    void case3(){

    }



}
