package com.project1.ticketing.concert;

import com.project1.ticketing.domain.concert.infrastructure.ConcertRepositoryImpl;
import com.project1.ticketing.domain.concert.infrastructure.ConcertTimeRepositoryImpl;
import com.project1.ticketing.domain.concert.infrastructure.SeatRepositoryImpl;
import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.models.SeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestDataHandler {


    ConcertRepositoryImpl concertRepositoryImpl;
    ConcertTimeRepositoryImpl concertTimeRepositoryImpl;
    SeatRepositoryImpl seatRepositoryImpl;



    @Autowired
    public TestDataHandler(ConcertRepositoryImpl concertRepositoryImpl, ConcertTimeRepositoryImpl concertTimeRepositoryImpl, SeatRepositoryImpl seatRepositoryImpl) {
        this.concertRepositoryImpl = concertRepositoryImpl;
        this.concertTimeRepositoryImpl = concertTimeRepositoryImpl;
        this.seatRepositoryImpl = seatRepositoryImpl;
    }

    void settingConcertInfo(){

        List<Seat> 나훈아_seatList1 = List.of(
                Seat.builder()
                        .seatNum(0L)
                        .concertHallId(0L)
                        .price(50000)
                        .status(SeatStatus.AVAILABLE)
                        .build(),
                Seat.builder()
                        .seatNum(1L)
                        .concertHallId(0L)
                        .price(50000)
                        .status(SeatStatus.AVAILABLE)
                        .build(),
                Seat.builder()
                        .seatNum(2L)
                        .concertHallId(0L)
                        .price(50000)
                        .status(SeatStatus.AVAILABLE)
                        .build()
        );
        List<Seat> 나훈아_seatList2 = List.of(
                Seat.builder()
                        .seatNum(3L)
                        .concertHallId(0L)
                        .price(50000)
                        .status(SeatStatus.AVAILABLE)
                        .build(),
                Seat.builder()
                        .seatNum(4L)
                        .concertHallId(0L)
                        .price(50000)
                        .status(SeatStatus.AVAILABLE)
                        .build(),
                Seat.builder()
                        .seatNum(5L)
                        .concertHallId(0L)
                        .price(50000)
                        .status(SeatStatus.AVAILABLE)
                        .build()
        );

        List<ConcertTime> 나훈아_concertTimeList = List.of(
                ConcertTime.builder()
                        .time(ConcertTime.fromStr("2024/04/20/ 17:00:00 KST"))
                        .seats(나훈아_seatList1)
                        .concertHallId(0L)
                        .maxSeatNum(50)
                        .currAvailableSeatNum(0)
                        .build(),
                ConcertTime.builder()
                        .time(ConcertTime.fromStr("2024/04/27/ 17:00:00 KST"))
                        .seats(나훈아_seatList2)
                        .concertHallId(0L)
                        .maxSeatNum(50)
                        .currAvailableSeatNum(0)
                        .build()
        );


        Concert concert = Concert.builder()
                .name("나훈아50주년콘서트")
                .concertTimeList(나훈아_concertTimeList)
                .build();


        concertRepositoryImpl.save(concert);
        concertTimeRepositoryImpl.save(나훈아_concertTimeList.get(0));
        concertTimeRepositoryImpl.save(나훈아_concertTimeList.get(1));

        for (Seat seat : 나훈아_seatList1) {
            seatRepositoryImpl.save(seat);
        }
        for (Seat seat : 나훈아_seatList2) {
            seatRepositoryImpl.save(seat);
        }


    }


}
