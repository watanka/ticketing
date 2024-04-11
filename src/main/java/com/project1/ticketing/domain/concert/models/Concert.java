package com.project1.ticketing.domain.concert.models;

import lombok.Getter;

@Getter
public class Concert{
    private long id;
    private String name;

    public Concert(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
