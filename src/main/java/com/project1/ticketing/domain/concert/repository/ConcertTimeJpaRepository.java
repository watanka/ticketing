package com.project1.ticketing.domain.concert.repository;

import com.project1.ticketing.domain.concert.models.ConcertTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConcertTimeJpaRepository extends JpaRepository<ConcertTime, Long> {
    ConcertTime save(ConcertTime concertTime);
    Optional<ConcertTime> findById(long concertTimeId);
    List<ConcertTime> findAllByConcertId(long concertId);

}
