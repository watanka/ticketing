package com.project1.ticketing.point;

import com.project1.ticketing.domain.point.components.PointService;
import com.project1.ticketing.domain.point.components.PointValidator;
import com.project1.ticketing.domain.point.models.Point;
import com.project1.ticketing.domain.point.models.PointType;
import com.project1.ticketing.domain.point.models.User;
import com.project1.ticketing.domain.point.repository.IPointRepository;
import com.project1.ticketing.domain.point.repository.IUserRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import static org.mockito.Mockito.when;

public class PointServiceTest {

    PointValidator pointValidator;
    IPointRepository pointRepository;
    IUserRepository userRepository;

    PointService pointService;

    User 신은성;
    Point 포인트사용_50000;
    Point 포인트충전_70000;

    @BeforeEach
    void setUp(){
        pointValidator = Mockito.mock(PointValidator.class);
        pointRepository = Mockito.mock(IPointRepository.class);
        userRepository = Mockito.mock(IUserRepository.class);

        pointService = new PointService(userRepository, pointRepository, pointValidator);

        신은성 = User.builder()
                .id(0L)
                .balance(4000)
                .build();

        포인트사용_50000 = Point.builder()
                .user(신은성)
                .amount(50000)
                .pointType(PointType.USE).build();

        포인트충전_70000 = Point.builder()
                .user(신은성)
                .amount(70000)
                .pointType(PointType.CHARGE).build();

    }

    @Test
    @DisplayName("포인트를 충전한다.")
    void case1(){
        when(userRepository.findById(0L)).thenReturn(Optional.ofNullable(신은성));


        Point point = pointService.updatePoint(0L, 포인트충전_70000.getAmount(), 포인트충전_70000.getPointType());

        assertThat(point.getAmount()).isEqualTo(70000L); // pointService에서 point만 리턴하기 때문에, user의 balance를 확인할 수가 없다. pointService에서 user와 point를 둘 다 넣는 게 문제인건지?
        pointService = new PointService(userRepository, pointRepository, pointValidator);

    }

    @Test
    @DisplayName("포인트를 조회한다.")
    void case2(){
        when(userRepository.findById(0L)).thenReturn(Optional.of(신은성));

        assertThat(pointService.checkPoint(0L)).isEqualTo(신은성.getBalance());
    }


    @Test
    @DisplayName("포인트 잔액이 부족해 사용에 실패한다.")
    void case3(){
        when(userRepository.findById(0L)).thenReturn(Optional.of(신은성));

        try{
            pointService.updatePoint(0L, 30000, PointType.USE);
        } catch (RuntimeException e){
            assertThat(e.getMessage()).isEqualTo("잔액이 부족합니다.");
        }



    }
}
