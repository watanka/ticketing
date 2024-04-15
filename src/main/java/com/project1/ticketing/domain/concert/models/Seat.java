package com.project1.ticketing.domain.concert.models;

public class Seat{
        long id;
        long concertHallId;
        long price;
        SeatStatus status;



    public Seat(long id, long concertHallId, long price, SeatStatus status) {
        this.id = id;
        this.concertHallId = concertHallId;
        this.price = price;
        this.status = status;
    }
}
