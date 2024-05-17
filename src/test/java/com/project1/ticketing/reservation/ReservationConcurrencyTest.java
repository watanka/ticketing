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

    Seat seat;
    ConcertTime concertTime;
    User user1;
    User user2;

    @Autowired
    ConcertCoreRepositoryImpl concertCoreRepository;

    @Autowired
    ReservationService reservationService;
    @Autowired
    ReservationCoreRepository reservationCoreRepository;

    @Autowired
    UserJpaRepository userJpaRepository;

    @BeforeEach
    void setup(){
        // 좌석 셋팅
        concertTime = new ConcertTime(0L,
                ConcertTime.fromStr("2024/04/13/ 20:30:00 KST")
                );
        seat = new Seat(0L, concertTime.getId(), 30000L);

        user1 = User.builder().balance(30000L).build();
        user2 = User.builder().balance(30000L).build();


        concertCoreRepository.saveConcertTime(concertTime);
        concertCoreRepository.saveSeat(seat);

        userJpaRepository.save(user1);
        userJpaRepository.save(user2);
        // 유저 셋팅
    }

    @Test
    void 동시에_하나의_좌석_예약시도() throws InterruptedException {

        int threadCount = 5;
        ReservationRequest reservationRequest1 = new ReservationRequest(user1.getId(), concertTime.getId(), seat.getId());
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

        Seat foundSeat = concertCoreRepository.findSeatById(seat.getId());
        System.out.println(foundSeat.getVersion());
        System.out.println("# Success: " + successCnt + " # Fail: " + failCnt);
        Assertions.assertThat(foundSeat.getStatus()).isEqualTo(SeatStatus.RESERVED);

    }

}

