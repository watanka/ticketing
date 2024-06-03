package com.project1.ticketing.domain.concert.models;

import com.project1.ticketing.api.dto.response.ConcertResponse;
import com.project1.ticketing.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Table(name="concert")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Concert extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="concert_id")
    private long id;


    private String name;

//    @OneToMany(mappedBy="concertId", cascade = CascadeType.ALL)
//    private List<ConcertTime> concertTimeList = new ArrayList<>();

    public Concert(String name){
        this.name = name;
    }



    public static Concert from(ConcertResponse concertResponse){
        return Concert.builder()
                .id(concertResponse.getId())
                .name(concertResponse.getName())
                .build();
    }





}
