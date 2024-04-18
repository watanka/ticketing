package com.project1.ticketing.api.dto.response;

import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
public class ConcertResponse {

    private long id;
    private String name;

    public ConcertResponse(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ConcertResponse from(Concert concert){
        return ConcertResponse.builder()
                .id(concert.getId())
                .name(concert.getName())
                .build();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConcertResponse that = (ConcertResponse) o;
        return id == that.id && Objects.equals(name, that.name);
    }
}
