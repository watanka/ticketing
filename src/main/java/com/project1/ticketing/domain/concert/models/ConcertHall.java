package com.project1.ticketing.domain.concert.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ConcertHall {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "concerthall")
    private List<Seat> seatList = new ArrayList<>();

    @OneToMany(mappedBy = "concerthall")
    private List<ConcertTime> concertTimeList = new ArrayList<>();

    @Builder
    public ConcertHall(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
