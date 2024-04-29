package com.project1.ticketing.reservation;

import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.repository.ConcertCoreRepository;
import com.project1.ticketing.domain.point.components.UserManager;
import com.project1.ticketing.domain.reservation.components.ReservationService;
import com.project1.ticketing.domain.reservation.infrastructure.ReservationCoreRepositoryImpl;
import com.project1.ticketing.domain.reservation.repository.ReservationCoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;

public class ReservationConcurrencyTest {

    Seat seat;
    ConcertTime concertTime;

    @Autowired
    ConcertCoreRepository concertCoreRepository;

    @Autowired
    ReservationService reservationService;
    @Autowired
    ReservationCoreRepository reservationCoreRepository;

    @Autowired
    UserManager userManager;

    @BeforeEach
    void setup(){
        // 좌석 셋팅
        concertTime = new ConcertTime(0L,
                ConcertTime.fromStr("2024/04/13/ 20:30:00 KST")
                );
        seat = new Seat(0L, concertTime.getId(), 30000L);


        concertCoreRepository.saveConcertTime(concertTime);
        concertCoreRepository.saveSeat(seat);
        // 유저 셋팅
    }



}

