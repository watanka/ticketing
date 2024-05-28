package com.project1.ticketing.domain.reservation.infrastructure;

import com.project1.ticketing.domain.reservation.models.Reservation;
import com.project1.ticketing.domain.reservation.models.ReservationStatus;
import com.project1.ticketing.domain.reservation.repository.ReservationCoreRepository;
import com.project1.ticketing.domain.reservation.repository.ReservationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReservationCoreRepositoryImpl implements ReservationCoreRepository {

    private final ReservationJpaRepository reservationRepository;

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }


    @Override
    public Reservation findById(long reservationId) {
        return reservationRepository.findById(reservationId);
    }

    @Override
    public List<Reservation> findAllByUserId(long userId) {
        return reservationRepository.findAllByUserId(userId);
    }

    @Override
    public Optional<Reservation> findBySeatId(long seatId) {
        return reservationRepository.findBySeatId(seatId);
    }

    @Override
    public List<Reservation> findAllByStatus(ReservationStatus status) {
        return reservationRepository.findAllByStatus(status);
    }


}
