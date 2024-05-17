package com.project1.ticketing.domain.reservation.event;

import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.models.SeatStatus;
import com.project1.ticketing.domain.reservation.infrastructure.ReservationCoreRepositoryImpl;
import com.project1.ticketing.domain.reservation.models.Reservation;
import com.project1.ticketing.domain.reservation.repository.ReservationCoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;




@Component
@RequiredArgsConstructor
public class ReservationEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public void reservationOccupy(ReservationEvent event){
        eventPublisher.publishEvent(event);
    }

}
