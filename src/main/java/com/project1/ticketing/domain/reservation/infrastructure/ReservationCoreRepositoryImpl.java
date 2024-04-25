package com.project1.ticketing.domain.reservation.infrastructure;

import com.project1.ticketing.domain.reservation.models.Reservation;
import com.project1.ticketing.domain.reservation.repository.ReservationCoreRepository;
import com.project1.ticketing.domain.reservation.repository.ReservationJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReservationCoreRepositoryImpl implements ReservationCoreRepository {

    ReservationJpaRepository reservationRepository;

    @Autowired
    public ReservationCoreRepositoryImpl(ReservationJpaRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }


    @Override
    public Optional<Reservation> findById(long reservationId) {
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

}
