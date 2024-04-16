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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

public class ConcertServiceTest {


    @Mock IConcertRepository concertRepository;
    @Mock IConcertTimeRepository concertTimeRepository;
    @Mock ISeatRepository seatRepository;
    @Mock IConcertHallRepository concertHallRepository;

    @InjectMocks
    ConcertService concertService;


    List<Concert> concertList;
    List<ConcertTime> concertTimeList;
    List<Seat> seatList;

    private Concert 나훈아_콘서트;
    private Concert 아일릿_콘서트;
    private List<ConcertTime> 나훈아_콘서트시간_리스트;
    private List<ConcertTime> 아일릿_콘서트시간_리스트;

    private ConcertHall 잠실_종합운동장;

    @BeforeEach
    void setup(){

        concertRepository = Mockito.mock(IConcertRepository.class);
        concertTimeRepository = Mockito.mock(IConcertTimeRepository.class);
        seatRepository = Mockito.mock(ISeatRepository.class);
        concertHallRepository = Mockito.mock(IConcertHallRepository.class);


        concertService = new ConcertService(
                concertRepository,
                concertTimeRepository,
                seatRepository,
                concertHallRepository
                );

        나훈아_콘서트 = Concert.builder()
                .id(0L)
                .name("나훈아50주년 콘서트")
                .build();

        나훈아_콘서트시간_리스트 = List.of(
                ConcertTime.builder()
                        .time(ConcertTime.fromStr("2024/04/15/ 17:00:00 KST"))
                        .concertId(0L)
                        .concertHallId(0L)
                        .maxSeatNum(50)
                        .currAvailableSeatNum(36).build(),
                ConcertTime.builder()
                        .time(ConcertTime.fromStr("2024/04/16/ 17:00:00 KST"))
                        .concertId(1L)
                        .concertHallId(0L)
                        .maxSeatNum(50)
                        .currAvailableSeatNum(36).build()
        );

        아일릿_콘서트 = Concert.builder()
                .id(0L)
                .name("아일릿 데뷔 콘서트")
                .build();

        아일릿_콘서트시간_리스트 = List.of(
                ConcertTime.builder()
                        .time(ConcertTime.fromStr("2024/04/20/ 17:00:00 KST"))
                        .concertId(2L)
                        .concertHallId(0L)
                        .maxSeatNum(50)
                        .currAvailableSeatNum(24).build(),
                ConcertTime.builder()
                        .time(ConcertTime.fromStr("2024/04/21/ 17:00:00 KST"))
                        .concertId(3L)
                        .concertHallId(0L)
                        .maxSeatNum(50)
                        .currAvailableSeatNum(22).build()
        );

        잠실_종합운동장 = ConcertHall.builder()
                .id(0L)
                .name("잠실 종합운동장")
                .build();

        seatList = List.of(
                new Seat(1L, 0L, 200000),
                new Seat(2L, 0L, 150000),
                new Seat(3L, 0L, 150000),
                new Seat(4L, 0L, 120000)
        );

    }

    @Test
    @DisplayName("전체 콘서트 리스트를 확인한다")
    void listConcerts(){
        //given & when
        when(concertRepository.getAll()).thenReturn(List.of(나훈아_콘서트, 아일릿_콘서트));
        List<Concert> concertList = concertService.getAllConcerts();

        //then
        assertThat(concertList.get(0).getName()).isEqualTo("나훈아50주년 콘서트");

    }


    @Test
    @DisplayName("선택한 콘서트의 날짜들을 확인한다.")
    void listConcertTimes(){
        long concertId = 0L;
        //given & when
        when(concertTimeRepository.getAllByConcertId(concertId)).thenReturn(나훈아_콘서트시간_리스트);
        //then
        assertThat(concertService.getAllTimes(concertId)).isEqualTo(나훈아_콘서트시간_리스트);
    }


    @Test
    @DisplayName("선택한 콘서트 날짜에 좌석들을 확인한다.")
    void listConcertSeats(){
        long concertId = 0L;
        long concertTimeId = 0L;
        long concertHallId = 0L;

        when(concertTimeRepository.getByTime(concertId, concertTimeId)).thenReturn(Optional.ofNullable(ConcertTime.builder().concertHallId(concertHallId).build()));
        when(seatRepository.getAllSeats(concertHallId)).thenReturn(seatList);
        //then
        assertThat(concertService.getAllSeats(concertId, concertTimeId)).isEqualTo(seatList);
    }

    @Test
    @DisplayName("선택한 콘서트 날짜를 찾지 못한 경우")
    void listConcertSeatsNotFound(){
        long concertId = 0L;
        long concertTimeId = 0L;
        long concertHallId = 0L;

        when(concertTimeRepository.getByTime(concertId, concertTimeId)).thenReturn(Optional.empty());
        //then
        try{
            concertService.getAllSeats(concertId, concertTimeId);
            fail();
        } catch (RuntimeException e){
            assertThat(e.getMessage()).isEqualTo("해당 콘서트 시간을 찾지 못 했습니다.");
        }

    }


}
