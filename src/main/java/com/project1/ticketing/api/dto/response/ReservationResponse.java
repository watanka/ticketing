package com.project1.ticketing.api.dto.response;

import com.project1.ticketing.domain.reservation.models.Reservation;
import com.project1.ticketing.domain.reservation.models.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
public class ReservationResponse {

    private List<Reservation> reservationList;
    private long userId;
    private ReservationStatus status;
    private ZonedDateTime concertTime;
    private String tokenId;
    private long seatId;
    private ZonedDateTime expiredAt;

    public ReservationResponse(long userId, ZonedDateTime concertTime, String tokenId, long seatId, ZonedDateTime expiredAt) {
        this.userId = userId;
        this.status = ReservationStatus.TEMPORARY_RESERVED;
        this.tokenId = tokenId;
        this.concertTime = concertTime;
        this.seatId = seatId;
        this.expiredAt = expiredAt;
    }

    public ReservationResponse(long userId, List<Reservation> reservationList) {
        this.userId = userId;
        this.reservationList = reservationList;
    }
}
