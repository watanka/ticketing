package com.project1.ticketing.domain.concert.models;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Seat{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        long id;

        @ManyToOne
        @JoinColumn(name = "concertime_id")
        private ConcertTime concertTime;


        long concertHallId;

        long price;



    public Seat(long id, long concertHallId, long price) {
        this.id = id;
        this.concertHallId = concertHallId;
        this.price = price;
    }
}
