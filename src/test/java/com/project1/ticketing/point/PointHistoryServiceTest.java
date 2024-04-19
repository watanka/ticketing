package com.project1.ticketing.point;

import com.project1.ticketing.api.dto.request.PointRequest;
import com.project1.ticketing.api.dto.response.PointHistoryResponse;
import com.project1.ticketing.domain.point.components.PointHistoryService;
import com.project1.ticketing.domain.point.components.PointValidator;
import com.project1.ticketing.domain.point.models.PointHistory;
import com.project1.ticketing.domain.point.models.PointType;
import com.project1.ticketing.domain.point.models.User;
import static org.assertj.core.api.Assertions.assertThat;

import com.project1.ticketing.domain.point.repository.PointCoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import static org.mockito.Mockito.when;

public class PointHistoryServiceTest {

    PointValidator pointValidator;
    PointCoreRepository pointRepository;

    PointHistoryService pointHistoryService;

    User 신은성;
    PointHistory 포인트사용_50000;
    PointHistory 포인트충전_70000;

    @BeforeEach
    void setUp(){
        pointValidator = Mockito.mock(PointValidator.class);
        pointRepository = Mockito.mock(PointCoreRepository.class);


        pointHistoryService = new PointHistoryService(pointRepository, pointValidator);

        신은성 = User.builder()
                .id(0L)
                .balance(4000)
                .build();

        포인트사용_50000 = PointHistory.builder()
                .userId(0L)
                .amount(50000)
                .pointType(PointType.USE).build();

        포인트충전_70000 = PointHistory.builder()
                .userId(0L)
                .amount(70000)
                .pointType(PointType.CHARGE).build();

    }

    @Test
    @DisplayName("포인트를 충전한다.")
    void case1(){
        long userId = 0L;
        long amount = 30000;

        when(pointValidator.validateUser(userId))
                .thenReturn(User.builder()
                                .id(userId)
                                .balance(0L)
                                .build());
        PointRequest pointRequest = new PointRequest(userId, amount, PointType.CHARGE);

        PointHistoryResponse pointHistoryResponse = pointHistoryService.updatePoint(pointRequest);


        assertThat(pointHistoryResponse.getAmount()).isEqualTo(amount); // pointService에서 point만 리턴하기 때문에, user의 balance를 확인할 수가 없다. pointService에서 user와 point를 둘 다 넣는 게 문제인건지?

    }

    @Test
    @DisplayName("포인트를 조회한다.")
    void case2(){
        when(pointValidator.validateUser(0L)).thenReturn(신은성);

        assertThat(pointHistoryService.checkBalance(0L).getAmount()).isEqualTo(신은성.getBalance());
    }
//

//
//
//    }
    @Test
    @DisplayName("포인트 잔액이 부족해 사용에 실패한다.")
    void case3(){
        // 현재 신은성의 잔액은 4000원
        PointRequest pointRequest = new PointRequest(0, 30000, PointType.USE);
        when(pointValidator.validateUser(0L)).thenReturn(신은성);
        try{
            pointHistoryService.updatePoint(pointRequest);
        } catch (RuntimeException e){
            assertThat(e.getMessage()).isEqualTo("잔액이 부족합니다.");
    }

    }
}
