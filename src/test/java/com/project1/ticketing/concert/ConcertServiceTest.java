package com.project1.ticketing.concert;

import com.project1.ticketing.api.dto.response.ConcertResponse;
import com.project1.ticketing.api.dto.response.ConcertTimeResponse;
import com.project1.ticketing.api.dto.response.SeatResponse;
import com.project1.ticketing.domain.concert.components.ConcertFilter;
import com.project1.ticketing.domain.concert.components.ConcertService;
import com.project1.ticketing.domain.concert.components.ConcertValidator;
import com.project1.ticketing.domain.concert.models.*;

import com.project1.ticketing.domain.concert.repository.IConcertHallRepository;
import com.project1.ticketing.domain.concert.repository.IConcertRepository;
import com.project1.ticketing.domain.concert.repository.IConcertTimeRepository;
import com.project1.ticketing.domain.concert.repository.ISeatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConcertServiceTest {


    IConcertRepository concertRepository;
    IConcertTimeRepository concertTimeRepository;
    ISeatRepository seatRepository;
    IConcertHallRepository concertHallRepository;

    ConcertFilter concertFilter;
    ConcertValidator concertValidator;
    ConcertService concertService;


    List<Concert> concertList;
    List<ConcertTime> concertTimeList;
    List<SeatResponse> fullReservedSeatList;
    List<SeatResponse> fullAvailableSeatList;

    private ConcertResponse 나훈아_콘서트;
    private ConcertResponse 아일릿_콘서트;
    private List<ConcertTimeResponse> 나훈아_콘서트시간_리스트;
    private List<ConcertTimeResponse> 아일릿_콘서트시간_리스트;

    private ConcertHall 잠실_종합운동장;

    List<ConcertTime> fromConcertTimeDTOtoEntity(List<ConcertTimeResponse> concertTimeResponse){
        return concertTimeResponse.stream()
                .map(ConcertTime::from)
                .collect(Collectors.toList());
    }

    List<Seat> fromSeatDTOtoEntity(List<SeatResponse> seatResponse){
        return seatResponse.stream()
                .map(Seat::from)
                .collect(Collectors.toList());
    }

    @BeforeEach
    void setup(){
//
        concertRepository = mock(IConcertRepository.class);
        concertTimeRepository = mock(IConcertTimeRepository.class);
        seatRepository = mock(ISeatRepository.class);

        concertValidator = new ConcertValidator(concertRepository, concertTimeRepository, seatRepository);
        concertFilter = new ConcertFilter(seatRepository);


        concertService = new ConcertService(
                concertRepository,
                concertTimeRepository,
                seatRepository,
                concertValidator,
                concertFilter
                );

        fullReservedSeatList = List.of(
                SeatResponse.builder().available(false).build(),
                SeatResponse.builder().available(false).build(),
                SeatResponse.builder().available(false).build(),
                SeatResponse.builder().available(false).build()
        );

        fullAvailableSeatList = List.of(
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
    @DisplayName("콘서트의 예약가능 날짜들을 확인한다.")
    void listAvailableConcertTimes(){
        long concertId = 0L;


        //given & when
        when(concertRepository.findById(concertId)).thenReturn(Optional.of(mock(Concert.class)));
        when(concertTimeRepository.getAllByConcertId(concertId)).thenReturn(fromConcertTimeDTOtoEntity(나훈아_콘서트시간_리스트));

        when(seatRepository.getAvailableSeatsbyConcertTimeId(anyLong())).thenReturn(Optional.of(Seat.builder().build()));
        //then
        assertThat(concertService.getAvailableTimes(concertId).size()).isEqualTo(2);
    }


    @Test
    @DisplayName("[전 좌석 예약가능]선택한 콘서트 날짜에 좌석들을 확인한다.")
    void listAvailableConcertSeats(){
        long concertTimeId = 0L;
        //given&when
        when(concertTimeRepository.findById(concertTimeId)).thenReturn(Optional.of(mock(ConcertTime.class)));
        when(seatRepository.getByConcertTimeId(concertTimeId)).thenReturn(fromSeatDTOtoEntity(fullAvailableSeatList));
        //then
        assertThat(concertService.getAvailableSeats(concertTimeId).size()).isEqualTo(fullAvailableSeatList.size());
    }

    @Test
    @DisplayName("[전 좌석 매진]선택한 콘서트 날짜에 좌석들을 확인한다.")
    void listAvailableConcertSeatsReturnNoSeat(){

        long concertTimeId = 0L;
        //given&when
        when(concertTimeRepository.findById(concertTimeId)).thenReturn(Optional.of(mock(ConcertTime.class)));
        when(seatRepository.getByConcertTimeId(concertTimeId)).thenReturn(fromSeatDTOtoEntity(fullReservedSeatList));
        //then
        assertThat(concertService.getAvailableSeats(concertTimeId).size()).isEqualTo(0);
    }

    @Test
    @DisplayName("선택한 콘서트 날짜를 찾지 못한 경우")
    void listConcertSeatsNotFound(){
        long concertTimeId = 0L;
        when(concertTimeRepository.findById(concertTimeId)).thenReturn(Optional.empty());
        //then
        try{
            concertService.getAvailableSeats(concertTimeId);
            fail();
        } catch (RuntimeException e){
            assertThat(e.getMessage()).isEqualTo("콘서트 시간 정보를 찾을 수 없습니다.");
        }

    }


}
