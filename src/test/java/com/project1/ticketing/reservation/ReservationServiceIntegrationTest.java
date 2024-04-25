package com.project1.ticketing.reservation;

import com.project1.ticketing.TestDataHandler;
import com.project1.ticketing.api.dto.request.ReservationRequest;
import com.project1.ticketing.api.dto.response.ReservationResponse;
import com.project1.ticketing.domain.concert.components.ConcertService;
import com.project1.ticketing.domain.concert.infrastructure.ConcertCoreRepositoryImpl;
import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.point.components.UserManager;
import com.project1.ticketing.domain.point.infrastructure.PointCoreRepositoryImpl;
import com.project1.ticketing.domain.reservation.components.ReservationService;
import com.project1.ticketing.domain.reservation.infrastructure.ReservationCoreRepositoryImpl;
import com.project1.ticketing.domain.reservation.models.Reservation;
import com.project1.ticketing.domain.reservation.models.ReservationStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Disabled
@SpringBootTest
@Transactional
@Rollback(false)
public class ReservationServiceIntegrationTest {

    @Autowired
    ReservationService reservationService;
    @Autowired
    ConcertCoreRepositoryImpl concertCoreRepository;

    @Autowired
    ConcertService concertService;
    @Autowired
    ReservationCoreRepositoryImpl reservationCoreRepository;


    @Autowired
    TestDataHandler testDataHandler;
    @Autowired
    PointCoreRepositoryImpl pointCoreRepository;

    @BeforeEach
    void setup(){
        testDataHandler.settingConcertInfo();
    }

    @Test
    @DisplayName("[좌석 예약]예약 내역 생성 확인")
    void case1(){

        long userId = 1L;
        long concertId = 1L;
        ConcertTime concertTime = ConcertTime.from(concertService.getAvailableTimes(concertId).get(0));
        Seat selectedSeat = Seat.from( concertService.getAvailableSeats(concertTime.getId()).get(0));

        ReservationRequest reservationRequest = new ReservationRequest(userId, concertTime.getId(), selectedSeat.getId());
        ReservationResponse reservationResponse = reservationService.register(reservationRequest);

        Reservation foundReservation = reservationCoreRepository.findById(reservationResponse.getId()).orElseThrow();

        Assertions.assertThat(foundReservation.getStatus()).isEqualTo(ReservationStatus.TEMPORARY);


    }


    @Test
    @DisplayName("[좌석 예약]좌석 상태 변경 확인")
    void case2(){

    }


    @Test
    @DisplayName("[좌석 예약]좌석 예약 후 일정 시간 초과시 예약 취소")
    void case3(){

    }

}
