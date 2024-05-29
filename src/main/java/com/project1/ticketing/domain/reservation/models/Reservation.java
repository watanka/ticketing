package com.project1.ticketing.domain.reservation.models;

import com.project1.ticketing.api.dto.response.ReservationResponse;
import com.project1.ticketing.domain.common.BaseEntity;
import com.project1.ticketing.domain.concert.models.Seat;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reservation_id")
    private long id;
    private long userId;

    private long concertId;
    private long concertTimeId;
    private long seatId;
    private long seatNum;

    private long price;
    private ZonedDateTime expiredAt;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Builder
    public Reservation(long userId, long concertId, long concertTimeId, long seatId, long seatNum, long price, ZonedDateTime expiredAt) {
        this.userId = userId;
        this.concertId = concertId;
        this.concertTimeId = concertTimeId;
        this.seatId = seatId;
        this.seatNum = seatNum;
        this.price = price;
        this.expiredAt = expiredAt;
        this.status = ReservationStatus.TEMPORARY;
    }


    public static Reservation from(ReservationResponse reservationResponse){
        return Reservation.builder()
                .userId(reservationResponse.userId())
                .concertTimeId(reservationResponse.concertTimeId())
                .expiredAt(reservationResponse.expiredAt())
                .build();
    }

}


