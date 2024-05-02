package com.project1.ticketing.point;

import com.project1.ticketing.api.dto.request.PointRequest;
import com.project1.ticketing.domain.point.components.PointHistoryService;
import com.project1.ticketing.domain.point.components.UserManager;
import com.project1.ticketing.domain.point.models.PointType;
import com.project1.ticketing.domain.point.models.User;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
@Transactional
public class PointConcurrencyTest {
    @Autowired
    PointHistoryService pointHistoryService;
    @Autowired
    UserManager userManager;

    @Test
    void 동시에_충전시_차례대로() throws InterruptedException {
        User user = new User(30000L);
        userManager.save(user);
        User foundUser = userManager.findById(1L).get();
        Assertions.assertThat(foundUser).isEqualTo(user);

        PointRequest pointRequest = new PointRequest(user.getId(), 10000, PointType.CHARGE);

        final int numberOfThreads = 3;
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(() -> {
                    pointHistoryService.updatePoint(pointRequest);
                    latch.countDown();
                    }
            );
        }
        latch.await();
        Thread.sleep(500);


        Assertions.assertThat(pointHistoryService.checkBalance(user.getId()).getAmount())
                .isEqualTo(30000 + 10000 * numberOfThreads);

        // 충돌 시나리오: 유저 포인트 충전시에 발생. 충전 버튼 중복 클릭
        // 유저 본인에 대해서만 처리하기 때문에 애초에 충돌은 많이 발생하지 않을 거다.


    }



}
