package com.project1.ticketing.domain.concert.infrastructure;

import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.models.SeatStatus;
import com.project1.ticketing.domain.concert.repository.ConcertJpaRepository;
import com.project1.ticketing.domain.concert.repository.ConcertTimeJpaRepository;
import com.project1.ticketing.domain.concert.repository.ConcertCoreRepository;
import com.project1.ticketing.domain.concert.repository.SeatJpaRepository;
import jakarta.persistence.LockModeType;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class ConcertCoreRepositoryImpl implements ConcertCoreRepository {


    ConcertJpaRepository concertJpaRepository;
    ConcertTimeJpaRepository concertTimeJpaRepository;
    SeatJpaRepository seatJpaRepository;


    @Override
    public void deleteAll(){concertJpaRepository.deleteAll();};

    @Override
    public Concert saveConcert(Concert concert) {
        return concertJpaRepository.save(concert);
    }

    @Override
    public ConcertTime saveConcertTime(ConcertTime concertTime) {
        return concertTimeJpaRepository.save(concertTime);
    }

    @Override
    public Seat saveSeat(Seat seat) {
        return seatJpaRepository.save(seat);
    }


    @Override
    public Concert findConcertById(long concertId) {
        return concertJpaRepository.findById(concertId);
    }

    @Override
    public ConcertTime findConcertTimeById(long concertTimeId) {
        return concertTimeJpaRepository.findById(concertTimeId);
    }

    @Override
    public List<Concert> findAllConcerts() {
        return concertJpaRepository.findAll();
    }

    @Lock(LockModeType.OPTIMISTIC)
    @Override
    public Seat findSeatById(long seatId) {
        return seatJpaRepository.findById(seatId);
    }

    @Override
    public List<ConcertTime> findAllConcertTimesByConcertId(long concertId) {
        return concertTimeJpaRepository.findAllByConcertId(concertId);
    }


    @Override
    public List<Seat> findAllSeatsByConcertTimeId(long concertTimeId) {
        return seatJpaRepository.findAllByConcertTimeId(concertTimeId);
    }

    @Override
    public boolean isconcertTimeAvailable(long concertTimeId) {
        return seatJpaRepository.existsByConcertTimeIdAndStatus(concertTimeId, SeatStatus.AVAILABLE);
    }

}
