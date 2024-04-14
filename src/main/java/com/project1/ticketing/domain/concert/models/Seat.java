package com.project1.ticketing.domain.concert.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public record Seat(
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       long id,
       long price
) {
}
