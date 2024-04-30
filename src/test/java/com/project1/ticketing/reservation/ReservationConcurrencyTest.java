package com.project1.ticketing.reservation;

import com.project1.ticketing.api.dto.request.ReservationRequest;
import com.project1.ticketing.domain.concert.infrastructure.ConcertCoreRepositoryImpl;
import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.models.SeatStatus;
import com.project1.ticketing.domain.concert.repository.ConcertCoreRepository;
import com.project1.ticketing.domain.point.components.UserManager;
import com.project1.ticketing.domain.point.models.User;
import com.project1.ticketing.domain.point.repository.UserJpaRepository;
import com.project1.ticketing.domain.reservation.components.ReservationService;
import com.project1.ticketing.domain.reservation.infrastructure.ReservationCoreRepositoryImpl;
import com.project1.ticketing.domain.reservation.repository.ReservationCoreRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

@SpringBootTest
@Transactional
@Rollback(false)
public class ReservationConcurrencyTest {

    Seat seat;
    ConcertTime concertTime;
    User user1;

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
        User user2 = User.builder().balance(30000L).build();


        concertCoreRepository.saveConcertTime(concertTime);
        concertCoreRepository.saveSeat(seat);

        userJpaRepository.save(user1);
        userJpaRepository.save(user2);
        // 유저 셋팅
    }

    @Test
    void 동시에_하나의_좌석_예약시도() {

        ReservationRequest reservationRequest1 = new ReservationRequest(user1.getId(), concertTime.getId(), seat.getId());

        CompletableFuture.runAsync(() -> reservationService.register(reservationRequest1));
        CompletableFuture.runAsync(() -> reservationService.register(reservationRequest1));

        Seat foundSeat = concertCoreRepository.findSeatById(seat.getId()).orElseThrow();

        Assertions.assertThat(foundSeat.getStatus()).isEqualTo(SeatStatus.RESERVED);

    }

}

