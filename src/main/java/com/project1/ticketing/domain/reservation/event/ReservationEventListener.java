package com.project1.ticketing.domain.reservation.event;

import com.project1.ticketing.domain.reservation.components.ReservationMonitor;
import jdk.jfr.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class ReservationEventListener {

    private final ReservationMonitor reservationMonitor;
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onApplicationEvent(ReservationEvent event){
        reservationMonitor.occupyReservation(event.getReservationId());
    }
}
