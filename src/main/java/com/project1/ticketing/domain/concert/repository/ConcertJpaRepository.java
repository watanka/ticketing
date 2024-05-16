package com.project1.ticketing.domain.concert.repository;

import com.project1.ticketing.domain.concert.models.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConcertJpaRepository extends JpaRepository<Concert, Long> {
    Concert save(Concert concert);
    Concert findById(long concertId);
    List<Concert> findAll();


}
