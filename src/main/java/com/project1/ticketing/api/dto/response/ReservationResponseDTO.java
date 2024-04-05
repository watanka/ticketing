package com.project1.ticketing.api.dto.response;

import com.project1.ticketing.domain.reservation.models.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class ReservationResponseDTO {
    private long userId;
    private ReservationStatus status;
    private ZonedDateTime concertTime;
    private long seatId;
    private ZonedDateTime expiredAt;

    public ReservationResponseDTO(long userId, ReservationStatus status, ZonedDateTime concertTime, long seatId, ZonedDateTime expiredAt) {
        this.userId = userId;
        this.status = status;
        this.concertTime = concertTime;
        this.seatId = seatId;
        this.expiredAt = expiredAt;
    }
}
