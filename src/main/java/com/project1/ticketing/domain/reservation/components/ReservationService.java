package com.project1.ticketing.domain.reservation.components;

import com.project1.ticketing.domain.reservation.infrastructure.MemoryReservationRepository;
import com.project1.ticketing.domain.reservation.models.Reservation;
import com.project1.ticketing.domain.reservation.repository.IReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    IReservationRepository IReservationRepository;

    @Autowired
    public ReservationService(MemoryReservationRepository IReservationRepository) {
        this.IReservationRepository = IReservationRepository;
    }

    void register(Reservation reservation){
        IReservationRepository.save(reservation);
        return;
    }

    void check(long reservationId){
        IReservationRepository.getById(reservationId);
    }

}
