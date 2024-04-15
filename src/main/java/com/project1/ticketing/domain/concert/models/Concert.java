package com.project1.ticketing.domain.concert.models;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Concert{
    private long id;
    private String name;
    private List<ConcertTime> concertTimeList;


    public Concert(long id, String name, List<ConcertTime> concertTimeList) {
        this.id = id;
        this.name = name;
        this.concertTimeList = concertTimeList;
    }
}
