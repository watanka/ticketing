package com.project1.ticketing.concert;

import com.project1.ticketing.api.dto.response.ConcertResponse;
import com.project1.ticketing.api.dto.response.ConcertTimeResponse;
import com.project1.ticketing.api.dto.response.SeatResponse;
import com.project1.ticketing.domain.concert.components.ConcertFilter;
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


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

public class ConcertServiceTest {


    @Mock IConcertRepository concertRepository;
    @Mock IConcertTimeRepository concertTimeRepository;
    @Mock ISeatRepository seatRepository;
    @Mock IConcertHallRepository concertHallRepository;

    ConcertFilter concertFilter;
    @InjectMocks
    ConcertService concertService;


    List<Concert> concertList;
    List<ConcertTime> concertTimeList;
    List<SeatResponse> fullReservedSeatList;
    List<SeatResponse> fullAvailableList;

    private ConcertResponse 나훈아_콘서트;
    private ConcertResponse 아일릿_콘서트;
    private List<ConcertTimeResponse> 나훈아_콘서트시간_리스트;
    private List<ConcertTimeResponse> 아일릿_콘서트시간_리스트;

    private ConcertHall 잠실_종합운동장;

    List<ConcertTime> fromDTOtoDomain(List<ConcertTimeResponse> concertTimeResponse){
        return concertTimeResponse.stream()
                .map(ConcertTime::from)
                .collect(Collectors.toList());
    }

    @BeforeEach
    void setup(){
//
        concertRepository = Mockito.mock(IConcertRepository.class);
        concertTimeRepository = Mockito.mock(IConcertTimeRepository.class);
        seatRepository = Mockito.mock(ISeatRepository.class);
        concertHallRepository = Mockito.mock(IConcertHallRepository.class);
        concertFilter = Mockito.mock(ConcertFilter.class);


        concertService = new ConcertService(
                concertRepository,
                concertTimeRepository,
                seatRepository,
                concertFilter,
                concertHallRepository
                );

        fullReservedSeatList = List.of(
                SeatResponse.builder().available(false).build(),
                SeatResponse.builder().available(false).build(),
                SeatResponse.builder().available(false).build(),
                SeatResponse.builder().available(false).build()
        );

        fullAvailableList = List.of(
                SeatResponse.builder().available(true).build(),
                SeatResponse.builder().available(true).build(),
                SeatResponse.builder().available(true).build(),
                SeatResponse.builder().available(true).build()
        );

        나훈아_콘서트시간_리스트 = List.of(
                ConcertTimeResponse.builder()
                        .id(0L)
                        .time(ConcertTime.fromStr("2024/04/15/ 17:00:00 KST"))
                        .maxSeatNum(50)
                        .currAvailableSeatNum(0).build(),
                ConcertTimeResponse.builder()
                        .time(ConcertTime.fromStr("2024/04/16/ 17:00:00 KST"))
                        .id(1L)
                        .maxSeatNum(50)
                        .currAvailableSeatNum(50).build()
        );



        나훈아_콘서트 = ConcertResponse.builder()
                .id(0L)
                .name("나훈아50주년 콘서트")
                .build();


        아일릿_콘서트 = ConcertResponse.builder()
                .id(0L)
                .name("아일릿 데뷔 콘서트")
                .build();

        아일릿_콘서트시간_리스트 = List.of(
                ConcertTimeResponse.builder()
                        .time(ConcertTime.fromStr("2024/04/20/ 17:00:00 KST"))
                        .id(2L)
                        .maxSeatNum(50)
                        .currAvailableSeatNum(24).build(),
                ConcertTimeResponse.builder()
                        .time(ConcertTime.fromStr("2024/04/21/ 17:00:00 KST"))
                        .id(3L)
                        .maxSeatNum(50)
                        .currAvailableSeatNum(22).build()
        );

        잠실_종합운동장 = ConcertHall.builder()
                .id(0L)
                .name("잠실 종합운동장")
                .build();



    }

    @Test
    @DisplayName("전체 콘서트 리스트를 확인한다")
    void listConcerts(){
        //given & when
        when(concertRepository.getAll()).thenReturn(List.of(Concert.from(나훈아_콘서트)));

        //then
        assertThat(concertService.getAllConcerts()).isEqualTo(List.of(나훈아_콘서트));

    }


    @Test
    @DisplayName("콘서트의 예약가능 날짜들을 확인한다.")
    void listAvailableConcertTimes(){
        long concertId = 0L;


        //given & when
        when(concertTimeRepository.getAllByConcertId(concertId)).thenReturn(fromDTOtoDomain(나훈아_콘서트시간_리스트));
        when(concertFilter.filterTime(fromDTOtoDomain(나훈아_콘서트시간_리스트))).thenReturn(나훈아_콘서트시간_리스트);
        //then
        assertThat(concertService.getAvailableTimes(concertId)).isEqualTo(나훈아_콘서트시간_리스트);
    }


    @Test
    @DisplayName("선택한 콘서트 날짜에 좌석들을 확인한다.")
    void listAvailableConcertSeats(){
        long concertTimeId = 0L;
        //given&when
        when(concertTimeRepository.getById(concertTimeId)).thenReturn(Optional.of(나훈아_콘서트시간_리스트.get(0)));
        when(seatRepository.getByConcertTimeId(concertTimeId)).thenReturn();
        //then
        assertThat(concertService.getAvailableSeats(concertTimeId)).isEqualTo(fullAvailableList);
    }

    @Test
    @DisplayName("[전 좌석 매진]선택한 콘서트 날짜에 좌석들을 확인한다.")
    void listAvailableConcertSeatsReturnNoSeat(){

        long concertTimeId = 0L;
        //given&when
        when(concertTimeRepository.getById(concertTimeId)).thenReturn(Optional.of(나훈아_콘서트시간_리스트.get(0)));
        when(seatRepository.getByConcertTimeId(concertTimeId)).thenReturn(fullReservedSeatList);
        //then
        assertThat(concertService.getAvailableSeats(concertTimeId).size()).isEqualTo(0);
    }

    @Test
    @DisplayName("선택한 콘서트 날짜를 찾지 못한 경우")
    void listConcertSeatsNotFound(){
        long concertId = 0L;
        long concertTimeId = 0L;
        long concertHallId = 0L;

        when(concertTimeRepository.getById(concertTimeId)).thenReturn(Optional.of(나훈아_콘서트시간_리스트.get(0)));
        when(concertTimeRepository.getById(concertTimeId)).thenReturn(Optional.empty());
        //then
        try{
            concertService.getAvailableSeats(concertTimeId);
            fail();
        } catch (RuntimeException e){
            assertThat(e.getMessage()).isEqualTo("해당 콘서트 시간을 찾지 못 했습니다.");
        }

    }


}
