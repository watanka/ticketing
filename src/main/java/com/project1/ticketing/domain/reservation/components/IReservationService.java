package com.project1.ticketing.domain.reservation.components;

import com.project1.ticketing.api.dto.request.ReservationRequestDTO;
import com.project1.ticketing.api.dto.response.ReservationResponseDTO;

public interface IReservationService {
    ReservationResponseDTO makeReservation(ReservationRequestDTO reservationRequestDTO);
    ReservationResponseDTO checkReservation(long userId);
}
