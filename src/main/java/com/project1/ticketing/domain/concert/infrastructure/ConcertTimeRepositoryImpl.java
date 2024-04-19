package com.project1.ticketing.domain.concert.infrastructure;

import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.repository.IConcertTimeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertTimeRepositoryImpl extends JpaRepository<ConcertTime, Long>, IConcertTimeRepository {
}
