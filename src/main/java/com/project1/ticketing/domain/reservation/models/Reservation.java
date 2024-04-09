package com.project1.ticketing.domain.reservation.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Setter
@Getter
@AllArgsConstructor
public class Reservation{
    private long id;
    private long userId;
    private String concertTime;
    private long seatId;
    private long price;
    private ReservationStatus status;
    private ZonedDateTime createAt;
}
