package com.project1.ticketing.domain.concert.models;

public class Seat{
        long id;
        long concertHallId;
        long price;



    public Seat(long id, long concertHallId, long price) {
        this.id = id;
        this.concertHallId = concertHallId;
        this.price = price;
    }
}
