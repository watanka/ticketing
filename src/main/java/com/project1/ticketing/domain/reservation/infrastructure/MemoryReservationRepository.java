package com.project1.ticketing.domain.reservation.infrastructure;

import com.project1.ticketing.domain.reservation.models.Reservation;
import com.project1.ticketing.domain.reservation.repository.IReservationRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class MemoryReservationRepository implements IReservationRepository {
    @Override
    public Optional<Reservation> getById(long reservationId) {
        return Optional.empty();
    }

    @Override
    public void save(Reservation reservation) {

    }
}
