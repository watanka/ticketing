package com.project1.ticketing.domain.concert.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.ZonedDateTime;

@Entity
public record ConcertTime(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        long id,
        ZonedDateTime time,
        long concertId,
        int maxSeatNum,
        int currAvailableSeatNum
) {
}
