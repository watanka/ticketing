package com.project1.ticketing.reservation;

import com.project1.ticketing.domain.reservation.components.ReservationService;
import com.project1.ticketing.domain.reservation.components.ReservationValidator;
import com.project1.ticketing.domain.reservation.models.Reservation;
import com.project1.ticketing.domain.reservation.repository.ReservationCoreRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class ReservationServiceTest {

    ReservationCoreRepository reservationRepository;
    ReservationValidator reservationValidator;
    ReservationService reservationService;

    List<Reservation> reservationList;
    @BeforeEach
    void setUp(){
        reservationValidator = Mockito.mock(ReservationValidator.class);
        reservationRepository = Mockito.mock(ReservationCoreRepository.class);
        reservationService = new ReservationService(reservationRepository, reservationValidator);

        reservationList = List.of(
                Reservation.builder().build(),
                Reservation.builder().build(),
                Reservation.builder().build()
                );

    }

    @Test
    @DisplayName("예약 조회")
    void 예약을_확인한다(){
        long userId = 0L;
        when(reservationRepository.findAllByUserId(userId)).thenReturn(reservationList);
        List<Reservation> reservations = reservationService.findAllByUserId(userId);

        Assertions.assertThat(reservations).isEqualTo(reservationList);
    }


    @Test
    @DisplayName("예약한 좌석 상태 변경 확인")
    void 예약한_좌석_상태_확인(){
        long concertTimeId = 0L;
        long seatId = 0L;


        when(reservationRepository.findByConcertTimeIdAndSeatId(concertTimeId, seatId))
                .thenReturn(Optional.of(Reservation.builder().build()));

        boolean isReserved = reservationService.checkReserved(concertTimeId, seatId);

        Assertions.assertThat(isReserved).isEqualTo(true);

    }

    @Test
    @DisplayName("예약후 일정 시간이 지나면, 좌석 임시 대기가 풀림")
    void 예약후_일정시간이_지나면_임시대기_해제(){
        long reservationId = 0L;

        ZonedDateTime registeredTime = ZonedDateTime.parse("2024-04-17T12:30:00+05:00", DateTimeFormatter.ISO_ZONED_DATE_TIME);

        Reservation reservation = Reservation.builder()
                .createAt(registeredTime)
                .id(reservationId)
                .build();

        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of( reservation));
        // TODO: 내부 시간 모킹 필요



    }

    @Test
    @DisplayName("예약 번호를 찾을 수 없음")
    void 예약번호를_찾을_수_없다(){

    }



}
