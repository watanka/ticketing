package com.project1.ticketing.domain.concert.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

//@Entity
@Getter
public class ConcertHall {

//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="concerthall_id")
    private Long id;
    private String name;

//    @OneToMany
//    @JoinColumn(name = "concerthall_id")
    private List<Seat> seatList = new ArrayList<>();

//    @OneToMany
//    @JoinColumn(name = "concerthall_id")
    private List<ConcertTime> concertTimeList = new ArrayList<>();

    @Builder
    public ConcertHall(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
