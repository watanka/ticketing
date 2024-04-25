package com.project1.ticketing.domain.reservation.components;

import com.project1.ticketing.api.dto.request.ReservationRequest;
import com.project1.ticketing.api.dto.response.ReservationResponse;

import java.util.List;

public interface IReservationService {


    ReservationResponse register(ReservationRequest request);


    List<ReservationResponse> checkReservationList(long userId);

    ReservationResponse check(long userId, long reservationId);

    boolean checkSeatReserved(long seatId);


}
