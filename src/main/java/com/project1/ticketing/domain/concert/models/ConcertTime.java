package com.project1.ticketing.domain.concert.models;

import java.time.ZonedDateTime;

public record ConcertTime(

        long id,
        long concertId,

        ZonedDateTime time,

        int maxSeatNum,
        int currAvailableSeatNum
) {
}