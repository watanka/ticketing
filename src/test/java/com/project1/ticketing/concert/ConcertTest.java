package com.project1.ticketing.concert;

import com.project1.ticketing.domain.concert.components.ConcertService;
import com.project1.ticketing.domain.concert.models.*;

import com.project1.ticketing.domain.concert.repository.IConcertHallRepository;
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
    IConcertHallRepository concertHallRepository;

    List<Concert> concertList;
    List<ConcertTime> concertTimeList;
    List<Seat> seatList;

    private Concert 나훈아_콘서트;
    private ConcertHall 잠실_종합운동장;

    @BeforeEach
    void setup(){
        concertRepository = Mockito.mock(IConcertRepository.class);
        concertTimeRepository = Mockito.mock(IConcertTimeRepository.class);
        seatRepository = Mockito.mock(ISeatRepository.class);


        concertService = new ConcertService(
                concertRepository,
                concertTimeRepository,
                seatRepository,
                concertHallRepository
                );

        나훈아_콘서트 = Concert.builder()
                .id(0L)
                .name("나훈아50주년 콘서트")
                .concertTimeList(List.of(
                        ConcertTime.builder()
                                .time(ConcertTime.fromStr("2024/04/15/ 17:00:00 KST"))
                                .concertId(0L)
                                .concertHallId(0L)
                                .maxSeatNum(50)
                                .currAvailableSeatNum(36).build(),
                        ConcertTime.builder()
                                .time(ConcertTime.fromStr("2024/04/16/ 17:00:00 KST"))
                                .concertId(0L)
                                .concertHallId(0L)
                                .maxSeatNum(50)
                                .currAvailableSeatNum(36).build()
                ))
                .build();

        잠실_종합운동장 = ConcertHall.builder()
                .concertHallId(0L)
                .name("잠실 종합운동장")
                .seatList(List.of(
                        new Seat(1L, 0L, 200000),
                        new Seat(2L, 0L, 150000),
                        new Seat(3L, 0L, 150000),
                        new Seat(4L, 0L, 120000)
                )).build();


    }

    @Test
    @DisplayName("상영중인 콘서트들을 확인한다")
    void listConcerts(){
        //given & when
        when(concertRepository.getAll()).thenReturn(List.of(나훈아_콘서트));
        List<Concert> concertList = concertService.getAllConcerts();

        //then
        assertThat(concertList.get(0).getName()).isEqualTo("나훈아50주년 콘서트");

    }


    @Test
    @DisplayName("선택한 콘서트의 예약가능한 날짜들을 확인한다.")
    void listConcertTimes(){
        long concertId = 0L;
        //given & when
        when(concertTimeRepository.getAllByConcertId(concertId)).thenReturn(concertTimeList);
        //then
        assertThat(concertService.getAllTimes(concertId)).isEqualTo(concertTimeList);
    }


    @Test
    @DisplayName("선택한 콘서트 날짜에 예약가능한 좌석들을 확인한다.")
    void listConcertSeats(){
        long concertId = 0L;
        long concertTimeId = 0L;
        long concertHallId = 0L;

        when(seatRepository.getAllSeats(concertHallId)).thenReturn(seatList);
        //then
        assertThat(concertService.getAllSeats(concertId, concertTimeId)).isEqualTo(seatList);

    }

//    @Test
//    @DisplayName("좌석의 예약상태를 확인한다.")
//


}
