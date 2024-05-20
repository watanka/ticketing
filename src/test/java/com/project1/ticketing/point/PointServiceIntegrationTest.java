package com.project1.ticketing.point;

import com.project1.ticketing.api.dto.request.PointRequest;
import com.project1.ticketing.api.dto.response.PointHistoryResponse;
import com.project1.ticketing.domain.point.components.PointHistoryService;
import com.project1.ticketing.domain.point.infrastructure.PointCoreRepositoryImpl;
import com.project1.ticketing.domain.point.models.PointType;
import com.project1.ticketing.domain.point.models.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Disabled
@SpringBootTest
@Transactional
@Rollback(false)
public class PointServiceIntegrationTest {

    @Autowired
    PointCoreRepositoryImpl pointCoreRepository;

    @Autowired
    PointHistoryService pointHistoryService;

    @Test
    @DisplayName("포인트 충전")
    void case1(){
        User 신은성 = User.builder()
                .id(1L)
                .balance(30000)
                .build();
        pointCoreRepository.saveUser(신은성);
        long userId = 신은성.getId();
        PointRequest 포인트충전요청_30000 = PointRequest.builder()
                                        .userId(userId)
                                        .amount(30000L)
                                        .pointType(PointType.CHARGE)
                                        .build();

        pointHistoryService.updatePoint(포인트충전요청_30000);


        PointHistoryResponse pointHistoryResponse = pointHistoryService.checkBalance(userId);

        assertThat(pointHistoryResponse.getAmount()).isEqualTo(60000);

    }

    @Test
    @DisplayName("[성공케이스]-포인트 사용(잔액>=사용포인트)")
    void case2(){
        User 신은성 = User.builder()
                .id(1L)
                .balance(30000)
                .build();
        pointCoreRepository.saveUser(신은성);
        long userId = 신은성.getId();
        PointRequest 포인트사용요청_30000 = PointRequest.builder()
                .userId(userId)
                .amount(30000L)
                .pointType(PointType.USE)
                .build();

        pointHistoryService.updatePoint(포인트사용요청_30000);

        PointHistoryResponse pointHistoryResponse = pointHistoryService.checkBalance(userId);

        assertThat(pointHistoryResponse.getAmount()).isZero();

    }

    @Test
    @DisplayName("[실패케이스]-포인트 사용(잔액<사용포인트)")
    void case3(){
        User 신은성 = User.builder()
                .id(1L)
                .balance(30000)
                .build();
        pointCoreRepository.saveUser(신은성);
        long userId = 신은성.getId();
        PointRequest 포인트사용요청_9000000 = PointRequest.builder()
                .userId(userId)
                .amount(9000000)
                .pointType(PointType.USE)
                .build();

        try{
            pointHistoryService.updatePoint(포인트사용요청_9000000);
        }catch (RuntimeException e){
            assertThat(e.getMessage()).isEqualTo("잔액이 부족합니다.");
        }

        PointHistoryResponse pointHistoryResponse = pointHistoryService.checkBalance(userId);

        assertThat(pointHistoryResponse.getAmount()).isEqualTo(30000L);
        assertThat(pointHistoryResponse.getPointType()).isEqualTo(PointType.CHECK.toString());
    }

}
