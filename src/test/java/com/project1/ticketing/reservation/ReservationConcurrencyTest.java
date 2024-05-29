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
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ReservationConcurrencyTest {
    @Autowired
    ConcertCoreRepositoryImpl concertCoreRepository;

    @Autowired
    ReservationService reservationService;


    @Test
    void 동시에_하나의_좌석_예약시도() throws InterruptedException {

        int threadCount = 5000;

        long seatId = 1L;
        long concertTimeId = 1L;

        AtomicInteger successCnt = new AtomicInteger(0);
        AtomicInteger failCnt = new AtomicInteger(0);

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);


        for (int i = 0; i < threadCount; i++) {
            AtomicLong userId = new AtomicLong(i);
            executorService.submit(() -> {
                try {
                    ReservationRequest reservationRequest1 = new ReservationRequest(userId.get(), concertTimeId, seatId);

                    ReservationResponse reservationResponse = reservationService.reserve(reservationRequest1);
                    System.out.println("reservation obtained by user" + reservationResponse.userId());
                    successCnt.incrementAndGet();
                }catch (RuntimeException e){
                    System.out.println("Error Message: " + e.getMessage());
                    failCnt.incrementAndGet();
                } finally {
                    latch.countDown();
                }
                    });
        }
        latch.await();

        Seat foundSeat = concertCoreRepository.findSeatById(seatId);
        System.out.println("# Success: " + successCnt + " # Fail: " + failCnt);

        assertThat(successCnt.get()).isEqualTo(1);
        assertThat(failCnt.get()).isEqualTo(threadCount-1);

        assertThat(foundSeat.getStatus()).isEqualTo(SeatStatus.RESERVED);

    }

    @Test
    @Transactional
    void 낙관적_락_버젼을_확인한다(){


        for (int i=0;i <10; i++){
            Seat seat = concertCoreRepository.findSeatById(1L);
            System.out.println(seat.getStatus());

            seat.changeStatus(SeatStatus.RESERVED);

            concertCoreRepository.saveSeat(seat);
            System.out.println(seat.getVersion());
        }



    }

}

