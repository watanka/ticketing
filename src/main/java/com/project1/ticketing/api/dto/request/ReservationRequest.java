package com.project1.ticketing.api.dto.request;

public record ReservationRequest(long userId, long concertTimeId, long seatId){}