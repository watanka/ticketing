package com.project1.ticketing.domain.reservation.models;

import com.project1.ticketing.api.dto.response.ReservationResponse;
import com.project1.ticketing.domain.common.BaseEntity;
import com.project1.ticketing.domain.concert.models.Seat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Getter @Setter

public class Reservation extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reservation_id")
    private long id;

//    @ManyToOne
    private long userId;


    private long concertId;
    private String concertTime;

    private long seatId;
    private long seatNum;
    private long price;
    private ZonedDateTime expiredAt;

//    @OneToOne

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Builder
    public Reservation(long id, long userId, long concertId, String concertTime, long seatId, long seatNum, long price, ZonedDateTime expiredAt, ReservationStatus status) {
        this.id = id;
        this.userId = userId;
        this.concertId = concertId;
        this.concertTime = concertTime;
        this.seatId = seatId;
        this.seatNum = seatNum;
        this.price = price;
        this.expiredAt = expiredAt;
        this.status = status;
    }




    protected Reservation(){
    }

    public static Reservation from(ReservationResponse reservationResponse){
        return Reservation.builder()
                .id(reservationResponse.getId())
                .userId(reservationResponse.getUserId())
                .concertTime(reservationResponse.getConcertTime())
                .expiredAt(reservationResponse.getExpiredAt())
                .build();
    }

}


