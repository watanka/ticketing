package com.project1.ticketing.domain.concert.models;

import com.project1.ticketing.api.dto.response.ConcertResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
public class Concert{
    @Id
    @GeneratedValue
    private long id;
    private String name;

//    @OneToMany(mappedBy = "concert")
    private List<ConcertTime> concertTimeList = new ArrayList<>();

    @Builder
    public Concert(long id, String name, List<ConcertTime> concertTimeList) {
        this.id = id;
        this.name = name;
        this.concertTimeList = concertTimeList;
    }

    public static Concert from(ConcertResponse concertResponse){
        return Concert.builder()
                .id(concertResponse.getId())
                .name(concertResponse.getName())
                .build();
    }





}
