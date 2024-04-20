package com.project1.ticketing.domain.reservation.infrastructure;

import com.project1.ticketing.domain.reservation.models.Reservation;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class MemoryReservationRepository {
    public Optional<Reservation> findById(long reservationId) {
        return Optional.empty();
    }

    public void save(Reservation reservation) {

    }
}
