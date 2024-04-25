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

    @BeforeEach
    void setUp(){

        concertRepository.deleteAll();
        // testDataHandler에 시험할 데이터 넣어놓음
        testDataHandler.settingConcertInfo();
    }

    @Test
    void testJpaRepositoryWorking(){
        Concert concert = Concert.builder().name("나훈아50주년콘서트1").build();
        Concert savedConcert = concertRepository.saveConcert(concert);
        Concert foundConcert = concertRepository.findConcertById(savedConcert.getId()).orElseThrow();

        Assertions.assertThat(foundConcert.getId()).isEqualTo(concert.getId());
        Assertions.assertThat(foundConcert.getName()).isEqualTo(concert.getName());
    }


    @Test
    void testSpringJpaRepositoryWorking(){
        Concert concert = Concert.builder().name("나훈아50주년콘서트2").build();
        Concert savedConcert = concertRepository.saveConcert(concert);
        Concert foundConcert = concertRepository.findConcertById(savedConcert.getId()).orElseThrow();

        assertThat(foundConcert.getId()).isEqualTo(concert.getId());
        assertThat(foundConcert.getName()).isEqualTo(concert.getName());
        assertThat(concert).isEqualTo(foundConcert);
    }


    @Test
    @DisplayName("콘서트 리스트 조회")
    void testGetConcertList(){
        Concert concert = Concert.builder()
                .name("나훈아50주년콘서트")
                .build();
        concertRepository.saveConcert(concert);

        List<ConcertResponse> concertResponseList = concertService.getAllConcerts();
        assertThat(concertResponseList.get(0).getName()).isEqualTo("나훈아50주년콘서트");

        }

    @Test
    @DisplayName("예약 가능한 콘서트 시간 조회")
    void testGetAvailableConcertTime(){
        // ConcertTimeList = [ConcertTime1: 전좌석 예약가능, concertTime2: 전좌석 매진]

        List<ConcertTimeResponse> concertTimeResponseList = concertService.getAvailableTimes(1L);


        assertThat(concertTimeResponseList.size()).isEqualTo(1);
        assertThat(concertTimeResponseList.get(0).getTime()).isEqualTo(ConcertTime.fromStr("2024/04/20/ 17:00:00 KST"));

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
