package com.project1.ticketing.domain.reservation.components;

import com.project1.ticketing.api.dto.request.ReservationRequest;
import com.project1.ticketing.api.dto.response.ReservationResponse;

public interface IReservationService {
    ReservationResponse makeReservation(ReservationRequest reservationRequest);
    ReservationResponse checkReservation(long userId);
}
