package com.project1.ticketing.domain.concert.models;

import com.project1.ticketing.api.dto.response.ConcertResponse;
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
public class Concert{
    @Id
    @GeneratedValue
    @Column(name="concert_id")
    private long id;
    private String name;

    @OneToMany(mappedBy="concertId", cascade = CascadeType.ALL)
    private List<ConcertTime> concertTimeList = new ArrayList<>();



    public static Concert from(ConcertResponse concertResponse){
        return Concert.builder()
                .id(concertResponse.getId())
                .name(concertResponse.getName())
                .build();
    }





}
