package com.project1.ticketing.domain.concert.models;

import java.time.ZonedDateTime;

public record ConcertTime(

        long id,
        ZonedDateTime time,
        long concertId,
        int maxSeatNum,
        int currAvailableSeatNum
) {
}
