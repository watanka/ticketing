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
    private long id;

    @ManyToOne
    private User user;

    @OneToOne
    private ConcertTime concertTime;
    @OneToOne
    private Seat seat;

    private ReservationStatus status;
    private ZonedDateTime createAt;

    public static Reservation makeReservation(User user,
                                              ConcertTime concertTime,
                                              Seat seat,
                                              ReservationStatus reservationStatus
                                              ){
        return Reservation.builder()
                .user(user)
                .concertTime(concertTime)
                .seat(seat)
                .status(reservationStatus)
                .createAt(ZonedDateTime.now())
                .build();
    }
}


