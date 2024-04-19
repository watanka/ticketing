package com.project1.ticketing.domain.concert.repository;

import com.project1.ticketing.domain.concert.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatJpaRepository extends JpaRepository<Seat, Long> {

}
