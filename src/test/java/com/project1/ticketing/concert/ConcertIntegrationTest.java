package com.project1.ticketing.concert;

import com.project1.ticketing.TestDataHandler;
import com.project1.ticketing.api.dto.response.ConcertResponse;
import com.project1.ticketing.api.dto.response.ConcertTimeResponse;
import com.project1.ticketing.api.dto.response.SeatResponse;
import com.project1.ticketing.domain.concert.components.ConcertService;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.infrastructure.ConcertCoreRepositoryImpl;
import com.project1.ticketing.domain.concert.models.Concert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Disabled
@SpringBootTest
@Transactional
class ConcertIntegrationTest {

    @Autowired
    ConcertCoreRepositoryImpl concertRepository;
    @Autowired
    TestDataHandler testDataHandler;
    @Autowired
    ConcertService concertService;

    @Test
    @DisplayName("콘서트 리스트 조회")
    void testGetConcertList(){
        List<ConcertResponse> concertResponseList = concertService.getAllConcerts();
        assertThat(concertResponseList.get(0).getName()).isEqualTo("아일릿 데뷔 콘서트");

        }

    @Test
    @DisplayName("예약 가능한 콘서트 시간 조회-{예약 가능좌석 보유}")
    void AvailableConcertTimeExists(){
        // ConcertTimeList = [ConcertTime1: 전좌석 예약가능, concertTime2: 전좌석 매진]

        List<ConcertTimeResponse> concertTimeResponseList = concertService.getAvailableTimes(1L);
        assertThat(concertTimeResponseList.size()).isEqualTo(1);

    }

    @Test
    @DisplayName("예약 가능한 콘서트 시간 조회-{전좌석 매진}")
    void NoAvailableConcertTime(){
        List<ConcertTimeResponse> concertTimeResponseList = concertService.getAvailableTimes(2L);


        assertThat(concertTimeResponseList.size()).isEqualTo(0);

    }

    @Test
    @DisplayName("콘서트 찾을 수 없음")
    void testNotExistConcertId(){
        // ConcertTimeList = [ConcertTime1: 전좌석 예약가능, concertTime2: 전좌석 매진]

        try{
            concertService.getAvailableTimes(3L);
        }catch (RuntimeException e){
            assertThat(e.getMessage()).isEqualTo("콘서트 정보를 찾을 수 없습니다.");
        }


    }

    @Test
    @DisplayName("예약 가능한 좌석 조회")
    void testGetAvailableSeat(){
        long concertTimeId = 1L;
        List<SeatResponse> seatResponseList = concertService.getAvailableSeats(concertTimeId);

        for (SeatResponse seatResponse : seatResponseList) {
            assertThat(seatResponse.isAvailable()).isTrue();
        }


    }






}
