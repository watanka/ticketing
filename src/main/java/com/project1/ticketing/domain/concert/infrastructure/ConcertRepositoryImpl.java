package com.project1.ticketing.domain.concert.infrastructure;

import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.models.SeatStatus;
import com.project1.ticketing.domain.concert.repository.ConcertJpaRepository;
import com.project1.ticketing.domain.concert.repository.ConcertTimeJpaRepository;
import com.project1.ticketing.domain.concert.repository.IConcertRepository;
import com.project1.ticketing.domain.concert.repository.SeatJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ConcertRepositoryImpl implements IConcertRepository {

    ConcertJpaRepository concertJpaRepository;
    ConcertTimeJpaRepository concertTimeJpaRepository;
    SeatJpaRepository seatJpaRepository;

    public ConcertRepositoryImpl(ConcertJpaRepository concertJpaRepository,
                                 ConcertTimeJpaRepository concertTimeJpaRepository,
                                 SeatJpaRepository seatJpaRepository) {
        this.concertJpaRepository = concertJpaRepository;
        this.concertTimeJpaRepository = concertTimeJpaRepository;
        this.seatJpaRepository = seatJpaRepository;
    }

    @Override
    public List<Concert> findAll() {
        return concertJpaRepository.findAll();
    }

    @Override
    public Optional<Concert> findConcertById(long concertId) {
        return concertJpaRepository.findById(concertId);
    }

    @Override
    public List<ConcertTime> findAllByConcertId(long concertId) {
        return concertTimeJpaRepository.findAllByConcertId(concertId);
    }

    @Override
    public Optional<ConcertTime> findConcertTimeById(long concertTimeId) {
        return concertTimeJpaRepository.findById(concertTimeId);
    }


    @Override
    public List<Seat> findAllSeatByConcertTimeId(long concertTimeId) {
        return seatJpaRepository.findAllByConcertTimeId(concertTimeId);
    }

    @Override
    public boolean isconcertTimeAvailable(long concertTimeId, SeatStatus status) {
        return seatJpaRepository.existsByConcertTimeIdAndStatus(concertTimeId, status);
    }
}
