package com.project1.ticketing.concert;

import com.project1.ticketing.domain.concert.components.IConcertService;
import com.project1.ticketing.domain.concert.models.Concert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

public class ConcertTest {

    MemoryConcertService concertService;
    @Autowired
    public ConcertTest(MemoryConcertService concertService) {
        this.concertService = concertService;
    }

    @Test
    @DisplayName("상영중인 콘서트들을 확인한다")
    void listConcerts(){
        //given
        concertService.addConcert(new Concert(0L, "나훈아50주년 콘서트"));
        concertService.addConcert(new Concert( 1L, "아일릿 데뷔 콘서트"));

        List<String> concertList = concertService.getConcertList();

        //when


        //then
        assertThat(concertList).isEqualTo(List.of("나훈아50주년 콘서트", "아일릿 데뷔 콘서트"));
    }


    @Test
    @DisplayName("선택한 콘서트의 예약가능한 날짜들을 확인한다.")
    void listConcertTimes(){
        long concertId = 0L;

        List<String> concertTimeList = concertService.getAvailableConcertTimeList(concertId);
//        assertThat(concertTimeList).isEqualTo
    }


//    @Test
//    @DisplayName("선택한 콘서트 날짜에 예약가능한 좌석들을 확인한다.")
//
//
//    @Test
//    @DisplayName("좌석의 예약상태를 확인한다.")
//


}
