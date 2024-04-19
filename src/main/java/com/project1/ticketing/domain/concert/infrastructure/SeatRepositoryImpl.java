package com.project1.ticketing.domain.concert.infrastructure;

import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.repository.ISeatRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepositoryImpl extends JpaRepository<Seat, Long>, ISeatRepository {
}
