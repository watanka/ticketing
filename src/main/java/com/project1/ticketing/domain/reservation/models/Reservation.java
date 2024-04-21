package com.project1.ticketing.domain.reservation.models;

import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.point.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Getter @Setter
@Builder
public class Reservation{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reservation_id")
    private long id;

//    @ManyToOne
    private long userId;


    private long concertId;
    private ZonedDateTime concertTime;

    private long seatNum;
    private long price;

    private ReservationStatus status;
    private ZonedDateTime createAt;

}


