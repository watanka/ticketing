package com.project1.ticketing.domain.concert.components;

import com.project1.ticketing.api.dto.response.ConcertResponse;
import com.project1.ticketing.api.dto.response.ConcertTimeResponse;
import com.project1.ticketing.api.dto.response.SeatResponse;
import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.models.SeatStatus;
import com.project1.ticketing.domain.concert.repository.ConcertCoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConcertService implements IConcertService{

    private final ConcertCoreRepository concertRepository;
    private final ConcertValidator concertValidator;
    private final ConcertFilter concertFilter;





    @Override
    public List<ConcertResponse> getAllConcerts() {

        return concertRepository.findAllConcerts().stream()
                .map(ConcertResponse::from)
                .toList();
    }

    @Override
    public List<ConcertTimeResponse> getAvailableTimes(long concertId) {
        concertValidator.validateConcert(concertId);
        List<ConcertTime> concertTimeList = concertRepository.findAllConcertTimesByConcertId(concertId);

        return concertFilter.filterAvailableTime(concertTimeList);

    }

    @Override
    public List<SeatResponse> getAvailableSeats(long concertTimeId) {
        concertValidator.validateConcertTime(concertTimeId);
        List<Seat> seatList = concertRepository.findAllSeatsByConcertTimeId(concertTimeId);

        return concertFilter.filterAvailableSeat(seatList);
    }

    public Seat findSeatByConcertTimeIdAndSeatNum(long concertTimeId, long seatNum){
        return concertRepository.findSeatByConcertTimeIdAndSeatNum(concertTimeId, seatNum);

    }

    @Override
    public void patchSeatStatus(Seat seat, SeatStatus status) {
        if (seat != null){
            seat.changeStatus(status);
        }
    }

    public Seat findSeatById(long seatId){
        return concertRepository.findSeatById(seatId);
    }


    public Concert registerConcert(String name){
        return concertRepository.saveConcert(new Concert(name));
    }

    public ConcertTime registerConcertTime(ZonedDateTime concertTime, long concertId){
        return concertRepository.saveConcertTime(
                new ConcertTime(concertTime, concertId)
        );
    }

    public Seat registerSeat(long seatNum, long concertTimeId, long price){
        return concertRepository.saveSeat(
                new Seat(seatNum, concertTimeId, price));
    }

}
