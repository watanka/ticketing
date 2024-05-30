package com.project1.ticketing.reservation;

import com.project1.ticketing.api.dto.request.ReservationRequest;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.models.SeatStatus;
import com.project1.ticketing.domain.concert.repository.ConcertCoreRepository;
import com.project1.ticketing.domain.point.components.UserManager;
import com.project1.ticketing.domain.reservation.components.ReservationService;
import com.project1.ticketing.domain.reservation.components.ReservationValidator;
import com.project1.ticketing.domain.reservation.event.ReservationEventPublisher;
import com.project1.ticketing.domain.reservation.infrastructure.ReservationCoreRepositoryImpl;
import com.project1.ticketing.domain.reservation.models.Reservation;
import com.project1.ticketing.domain.reservation.repository.ReservationCoreRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.redisson.api.RedissonClient;

import static org.mockito.Mockito.when;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class ReservationServiceTest {

    ReservationCoreRepository reservationRepository;
    ReservationValidator reservationValidator;

    ConcertCoreRepository concertRepository;
    UserManager userManager;

    ReservationEventPublisher reservationEventPublisher;
    ReservationService reservationService;

    List<Reservation> reservationList;
    RedissonClient redissonClient;

    @BeforeEach
    void setUp(){
        reservationRepository = Mockito.mock(ReservationCoreRepository.class);
        reservationValidator = Mockito.mock(ReservationValidator.class);
        concertRepository = Mockito.mock(ConcertCoreRepository.class);
        userManager = Mockito.mock(UserManager.class);
        reservationEventPublisher = Mockito.mock(ReservationEventPublisher.class) ;
        redissonClient = Mockito.mock(RedissonClient.class);
        reservationService = new ReservationService(reservationRepository,
                                                    reservationValidator,
                                                    concertRepository,
                                                    userManager,
                                                    redissonClient,
                                                    reservationEventPublisher
                                                    );

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
        long seatId = 0L;

        when(concertRepository.findSeatById(seatId))
                .thenReturn(Seat.builder().status(SeatStatus.AVAILABLE).build());

        boolean isAvailable = reservationService.checkSeatReserved(seatId);

        Assertions.assertThat(isAvailable).isEqualTo(true);


    }

    @Test
    @DisplayName("예약후 일정 시간이 지나면, 좌석 임시 대기가 풀림")
    void 예약후_일정시간이_지나면_임시대기_해제(){
        long reservationId = 0L;

        ZonedDateTime registeredTime = ZonedDateTime.parse("2024-04-17T12:30:00+05:00", DateTimeFormatter.ISO_ZONED_DATE_TIME);

        Reservation reservation = Reservation.builder()
                .expiredAt(ZonedDateTime.now().plusMinutes(5))
                .build();

        when(reservationRepository.findById(reservationId)).thenReturn(reservation);
        // TODO: 내부 시간 모킹 필요



    }

    @Test
    @DisplayName("예약 번호를 찾을 수 없음")
    void 예약번호를_찾을_수_없다(){

    }



}
