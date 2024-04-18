package com.project1.ticketing.domain.concert.components;

import com.project1.ticketing.api.dto.response.ConcertResponse;
import com.project1.ticketing.api.dto.response.ConcertTimeResponse;
import com.project1.ticketing.api.dto.response.SeatResponse;
import com.project1.ticketing.api.utils.exceptions.BaseException;
import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.models.SeatStatus;
import com.project1.ticketing.domain.concert.repository.IConcertHallRepository;
import com.project1.ticketing.domain.concert.repository.IConcertRepository;
import com.project1.ticketing.domain.concert.repository.IConcertTimeRepository;
import com.project1.ticketing.domain.concert.repository.ISeatRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ConcertService implements IConcertService{

    IConcertRepository concertRepository;
    IConcertTimeRepository concertTimeRepository;
    ISeatRepository seatRepository;

    ConcertFilter concertFilter;
    IConcertHallRepository concertHallRepository;

    public ConcertService(IConcertRepository concertRepository,
                          IConcertTimeRepository concertTimeRepository,
                          ISeatRepository seatRepository,
                          ConcertFilter concertFilter,
                          IConcertHallRepository concertHallRepository) {
        this.concertRepository = concertRepository;
        this.concertTimeRepository = concertTimeRepository;
        this.seatRepository = seatRepository;
        this.concertFilter = concertFilter;
        this.concertHallRepository = concertHallRepository;
    }

    public List<ConcertResponse> getAllConcerts() {

        return concertRepository.getAll().stream()
                .map(ConcertResponse::from)
                .collect(Collectors.toList());
    }

    public List<ConcertTimeResponse> getAvailableTimes(long concertId) {
        // 전체 콘서트 시간을 불러온다.
        List<ConcertTime> concertTimeList = concertTimeRepository.getAllByConcertId(concertId);
        // 예약이 가능한 시간만 필터링한다.


        return concertFilter.filterTime(concertTimeList);

    }

    public List<SeatResponse> getAvailableSeats(long concertTimeId) {
        Optional<ConcertTime> concertTime = concertTimeRepository.getById(concertTimeId);

        if (concertTime.isEmpty()){
            throw new RuntimeException("해당 콘서트 시간을 찾지 못 했습니다.");
        }

        long concerTimeId = concertTime.get().getId();
        List<Seat> seatList = seatRepository.getByConcertTimeId(concerTimeId);

        return concertFilter.filterSeat(seatList);



    }


    // 콘서트, 시간, 좌석 순 꽉 찼는지 확인
    public boolean isConcertFull(long concertId){
        return concertRepository.isFull(concertId);
    }



    public boolean isSeatReserved(long concertId, long concertTime, long seatId){
        return seatRepository.isAvailable(concertId, concertTime, seatId);
    }

}
