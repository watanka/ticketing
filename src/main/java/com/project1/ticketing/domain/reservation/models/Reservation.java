package com.project1.ticketing.domain.reservation.models;

import com.project1.ticketing.api.dto.response.ReservationResponse;
import com.project1.ticketing.domain.concert.models.Seat;
import jakarta.persistence.*;
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
    private String concertTime;

    private long seatId;
    private long seatNum;
    private long price;
    private String expiredAt;

//    @OneToOne


    @Enumerated
    private ReservationStatus status;
    private ZonedDateTime createAt;

    public static Reservation from(ReservationResponse reservationResponse){
        return Reservation.builder()
                .id(reservationResponse.getId())
                .userId(reservationResponse.getUserId())
                .concertTime(reservationResponse.getConcertTime())
                .expiredAt(reservationResponse.getExpiredAt())
                .build();
    }

}


