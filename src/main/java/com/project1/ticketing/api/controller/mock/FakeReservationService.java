package com.project1.ticketing.api.controller.mock;

import com.project1.ticketing.api.dto.request.ReservationRequest;
import com.project1.ticketing.api.dto.response.ReservationResponse;
import com.project1.ticketing.domain.reservation.components.IReservationService;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class FakeReservationService implements IReservationService {


    @Override
    public ReservationResponse reserve(ReservationRequest request) {
        return ReservationResponse.builder()
                .id(0L)
                .userId(request.getUserId())
                .seatNum(request.getSeatId())
                .price(300000)
                .status("TEMPORARY")
                .concertTime("2024/04/20 17:00:00 KST")
                .expiredAt(ZonedDateTime.parse( "2024/04/17 15:30:00 KST"))
                .build();
    }

    @Override
    public List<ReservationResponse> checkReservationList(long userId) {
        return List.of(
                ReservationResponse.builder()
                        .id(0L)
                        .userId(0)
                        .seatNum(12)
                        .price(300000)
                        .status("REGISTERED")
                        .concertTime("2024/04/20 17:00:00 KST")
//                        .expiredAt("2024/04/17 15:30:00 KST") // if registered, No ExpiredAt
                        .build(),
                ReservationResponse.builder()
                        .id(0L)
                        .userId(0)
                        .seatNum(13)
                        .price(300000)
                        .status("TEMPORARY")
                        .concertTime("2024/04/20 17:00:00 KST")
                        .expiredAt(ZonedDateTime.parse( "2024/04/17 15:30:00 KST"))
                        .build(),
                ReservationResponse.builder()
                        .id(0L)
                        .userId(0)
                        .seatNum(14)
                        .price(300000)
                        .status("TEMPORARY")
                        .concertTime("2024/04/20 17:00:00 KST")
                        .expiredAt(ZonedDateTime.parse( "2024/04/17 15:30:00 KST"))
                        .build()
        );
    }

    @Override
    public ReservationResponse check(long userId, long reservationId) {
        return  ReservationResponse.builder()
                                    .id(0L)
                                    .userId(0)
                                    .seatNum(14)
                                    .price(300000)
                                    .status("TEMPORARY")
                                    .concertTime("2024/04/20 17:00:00 KST")
                                    .expiredAt(ZonedDateTime.parse( "2024/04/17 15:30:00 KST"))
                                    .build();
    }

    @Override
    public boolean checkSeatReserved(long seatId) {
        return true;
    }
}
