package com.project1.ticketing.domain.concert.repository;
import com.project1.ticketing.domain.concert.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ISeatRepository extends JpaRepository<Seat, Long> { // seat는 concertTime에 대한 정보를 모른다.

}