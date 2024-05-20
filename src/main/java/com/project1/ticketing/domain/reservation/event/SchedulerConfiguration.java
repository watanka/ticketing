package com.project1.ticketing.domain.reservation.event;

import com.project1.ticketing.domain.reservation.components.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchedulerConfiguration {
    private final ReservationService reservationService;

    @Scheduled(fixedDelay = 1 * 60 * 1000 )
    public void run(){
        reservationService.updateReservationStatus();
    }


}
