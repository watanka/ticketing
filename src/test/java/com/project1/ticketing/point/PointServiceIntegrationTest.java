package com.project1.ticketing.point;

import com.project1.ticketing.TestDataHandler;
import com.project1.ticketing.api.dto.request.PointRequest;
import com.project1.ticketing.api.dto.response.PointHistoryResponse;
import com.project1.ticketing.domain.point.components.PointHistoryService;
import com.project1.ticketing.domain.point.components.PointValidator;
import com.project1.ticketing.domain.point.infrastructure.PointCoreRepositoryImpl;
import com.project1.ticketing.domain.point.models.PointType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class PointServiceIntegrationTest {

    @Autowired
    PointCoreRepositoryImpl pointCoreRepository;

    @Autowired
    PointHistoryService pointHistoryService;

    @Autowired
    TestDataHandler testDataHandler;

    @BeforeEach
    void setUp(){
        pointCoreRepository.deleteAll();
        testDataHandler.settingPointInfo();
    }


    @Test
    @DisplayName("포인트 충전")
    void case1(){
        long userId = 1L;
        // 신은성은 30000포인트를 가지고 있다.
        PointRequest 충전요청_30000 = PointRequest.builder()
                                        .userId(userId)
                .amount(30000L)
                .pointType(PointType.CHARGE)
                                        .build();

        pointHistoryService.updatePoint(충전요청_30000);


        PointHistoryResponse pointHistoryResponse = pointHistoryService.checkBalance(userId);

        assertThat(pointHistoryResponse.getAmount()).isEqualTo(60000);

    }
}
