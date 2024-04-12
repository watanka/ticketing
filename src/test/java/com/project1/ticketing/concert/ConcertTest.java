package com.project1.ticketing.concert;

import com.project1.ticketing.domain.concert.components.ConcertService;
import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.repository.IConcertRepository;
import com.project1.ticketing.domain.concert.repository.IConcertTimeRepository;
import com.project1.ticketing.domain.concert.repository.ISeatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

public class ConcertTest {

    ConcertService concertService;
    IConcertRepository concertRepository;
    IConcertTimeRepository concertTimeRepository;
    ISeatRepository seatRepository;

    List<Concert> concertList;
    List<ConcertTime> concertTimeList;
    List<Seat> seatList;

    @BeforeEach
    void setup(){
        concertRepository = Mockito.mock(IConcertRepository.class);
        concertTimeRepository = Mockito.mock(IConcertTimeRepository.class);
        seatRepository = Mockito.mock(ISeatRepository.class);


        concertService = new ConcertService(
                concertRepository,
                concertTimeRepository,
                seatRepository
                );
        concertList = List.of(
                        new Concert(0L, "나훈아50주년 콘서트"),
                        new Concert( 1L, "아일릿 데뷔 콘서트")
        );

        long idCnt = 0;
        concertTimeList = List.of(
                new ConcertTime(
                        idCnt++,
                        ZonedDateTime.parse("2024/04/13/ 20:30:00 KST", DateTimeFormatter.ofPattern("yyyy/MM/dd/ HH:mm:ss z")),
                        0L, 50, 9),
                        new  ConcertTime(
                                idCnt++,
                                ZonedDateTime.parse("2024/04/14/ 18:30:00 KST",  DateTimeFormatter.ofPattern("yyyy/MM/dd/ HH:mm:ss z")),
                                0L, 50, 13)
                        );
        long seatNoCnt = 0L;
        seatList = List.of(
                new Seat(seatNoCnt++, 50000),
                new Seat(seatNoCnt++, 80000)
        );

    }

    @Test
    @DisplayName("상영중인 콘서트들을 확인한다")
    void listConcerts(){
        //given & when
        when(concertRepository.getAll()).thenReturn(concertList);


        //then
        assertThat(concertService.getConcertList()).isEqualTo(concertList);

    }


    @Test
    @DisplayName("선택한 콘서트의 예약가능한 날짜들을 확인한다.")
    void listConcertTimes(){
        long concertId = 0L;
        //given & when
        when(concertTimeRepository.getAllByConcertId(concertId)).thenReturn(concertTimeList);
        //then
        assertThat(concertService.getAllConcertTimeList(concertId)).isEqualTo(concertTimeList);
    }


    @Test
    @DisplayName("선택한 콘서트 날짜에 예약가능한 좌석들을 확인한다.")
    void listConcertSeats(){
        long concertId = 0L;
        long concertTimeId = 0L;

        when(seatRepository.getAllByConcertAndConcertTime(concertId, concertTimeId)).thenReturn(seatList);
        //then
        assertThat(concertService.getAvailableSeatList(concertId, concertTimeId)).isEqualTo(seatList);

    }

//    @Test
//    @DisplayName("좌석의 예약상태를 확인한다.")
//


}
