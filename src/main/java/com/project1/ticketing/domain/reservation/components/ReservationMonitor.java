package com.project1.ticketing.domain.reservation.components;

import com.project1.ticketing.domain.concert.components.ConcertService;
import com.project1.ticketing.domain.concert.models.SeatStatus;
import com.project1.ticketing.domain.reservation.models.Reservation;
import com.project1.ticketing.domain.reservation.models.ReservationStatus;
import com.project1.ticketing.domain.reservation.repository.ReservationCoreRepository;

public class ReservationMonitor {
    ReservationCoreRepository reservationCoreRepository;
    ConcertService concertService;


    public void occupyReservation(long reservationId){
        Reservation reservation = reservationCoreRepository.findById(reservationId);
        Long seatId = reservation.getSeatId();

        reservation.setStatus(ReservationStatus.REGISTERED);
        concertService.patchSeatStatus(seatId, SeatStatus.RESERVED);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(5 * 60 * 1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                reservation.setStatus(ReservationStatus.TEMPORARY);
                concertService.patchSeatStatus(seatId, SeatStatus.AVAILABLE);
            }
        });

        thread.start();


    }
}
