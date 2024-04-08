package com.project1.ticketing.domain.reservation.models;

import java.time.ZonedDateTime;

public record Reservation(
        long id,
        long userId,
        long concertTimeId,
        long seatId

) {
    //    createAt;
    //    updatedAt;
    //    status;
}
