package com.project1.ticketing.domain.concert.models;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ConcertHall {

    Long concertHallId;
    String name;
    List<Seat> seatList;


}
