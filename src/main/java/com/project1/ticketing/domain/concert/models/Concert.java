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
public class Concert{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany(mappedBy = "concert")
    private List<ConcertTime> concertTimeList = new ArrayList<>();

    @Builder
    public Concert(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
