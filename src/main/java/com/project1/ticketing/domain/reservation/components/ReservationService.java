package com.project1.ticketing.domain.reservation.components;

import com.project1.ticketing.domain.reservation.models.Reservation;
import com.project1.ticketing.domain.reservation.repository.ReservationRepository;

public class ReservationService {

    ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    void register(Reservation reservation){
        reservationRepository.save(reservation);
        return;
    }

    void check(long reservationId){
        reservationRepository.getById(reservationId);
    }

}
