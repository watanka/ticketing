package com.project1.ticketing.domain.concert.models;

public record ConcertTime(

        long id,
        long concertId,
        int maxSeatNum,
        int currAvailableSeatNum
) {
}
