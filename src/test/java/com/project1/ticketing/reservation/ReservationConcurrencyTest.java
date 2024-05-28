package com.project1.ticketing.reservation;

import com.project1.ticketing.api.dto.request.ReservationRequest;
import com.project1.ticketing.api.dto.response.ReservationResponse;
import com.project1.ticketing.domain.concert.infrastructure.ConcertCoreRepositoryImpl;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.models.SeatStatus;
import com.project1.ticketing.domain.point.models.User;
import com.project1.ticketing.domain.point.repository.UserJpaRepository;
import com.project1.ticketing.domain.reservation.components.ReservationService;
import com.project1.ticketing.domain.reservation.repository.ReservationCoreRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
public class ReservationConcurrencyTest {
    @Autowired
    ConcertCoreRepositoryImpl concertCoreRepository;

    @Autowired
    ReservationService reservationService;


    @Test
    void 동시에_하나의_좌석_예약시도() throws InterruptedException {

        int threadCount = 20;

        long seatId = 1L;
        long concertTimeId = 1L;
        long userId = 1L;

        ReservationRequest reservationRequest1 = new ReservationRequest(userId, concertTimeId, seatId);
//

        AtomicInteger successCnt = new AtomicInteger(0);
        AtomicInteger failCnt = new AtomicInteger(0);

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);


        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    ReservationResponse reservationResponse = reservationService.reserve(reservationRequest1);
                    successCnt.incrementAndGet();
                }catch (Exception e){
                    System.out.println("Catch Optimistic Exception!");
                    System.out.println("Error Message: " + e.getMessage());
                    failCnt.incrementAndGet();
                }finally {
                            latch.countDown();
                        }
                    });
        }
        latch.await();

//        reservationService.register(reservationRequest1);
//        CompletableFuture.allOf(
//            CompletableFuture.runAsync(() -> reservationService.register(reservationRequest1))
////        CompletableFuture.runAsync(() -> reservationService.register(reservationRequest1))
//        ).join();

        Seat foundSeat = concertCoreRepository.findSeatById(seatId);
        System.out.println(foundSeat);
        System.out.println("# Success: " + successCnt + " # Fail: " + failCnt);
        Assertions.assertThat(foundSeat.getStatus()).isEqualTo(SeatStatus.RESERVED);

    }

}

