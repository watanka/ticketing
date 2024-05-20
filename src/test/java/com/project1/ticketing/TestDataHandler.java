package com.project1.ticketing;

import com.project1.ticketing.domain.concert.infrastructure.ConcertCoreRepositoryImpl;
import com.project1.ticketing.domain.concert.models.Concert;
import com.project1.ticketing.domain.concert.models.ConcertTime;
import com.project1.ticketing.domain.concert.models.Seat;
import com.project1.ticketing.domain.concert.models.SeatStatus;
import com.project1.ticketing.domain.point.infrastructure.PointCoreRepositoryImpl;
import com.project1.ticketing.domain.point.models.PointHistory;
import com.project1.ticketing.domain.point.models.PointType;
import com.project1.ticketing.domain.point.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestDataHandler {


    ConcertCoreRepositoryImpl concertRepositoryImpl;
    PointCoreRepositoryImpl pointRepositoryImpl;


    @Autowired
    public TestDataHandler(ConcertCoreRepositoryImpl concertRepositoryImpl,
                           PointCoreRepositoryImpl pointRepositoryImpl
                           ) {
        this.concertRepositoryImpl = concertRepositoryImpl;
        this.pointRepositoryImpl = pointRepositoryImpl;

    }

    public void settingConcertInfo(){

        List<Seat> 전좌석예약가능 = List.of(
                Seat.builder()
                        .seatNum(0L)
                        .concertTimeId(1L)
//                        .concertHallId(0L)
                        .price(50000)
                        .status(SeatStatus.AVAILABLE)
                        .build(),
                Seat.builder()
                        .seatNum(1L)
                        .concertTimeId(1L)
//                        .concertHallId(0L)
                        .price(50000)
                        .status(SeatStatus.AVAILABLE)
                        .build(),
                Seat.builder()
                        .seatNum(2L)
                        .concertTimeId(1L)
//                        .concertHallId(0L)
                        .price(50000)
                        .status(SeatStatus.AVAILABLE)
                        .build()
        );
        List<Seat> 전좌석_매진 = List.of(
                Seat.builder()
                        .seatNum(3L)
                        .concertTimeId(2L)
//                        .concertHallId(0L)
                        .price(50000)
                        .status(SeatStatus.RESERVED)
                        .build(),
                Seat.builder()
                        .seatNum(4L)
                        .concertTimeId(2L)
//                        .concertHallId(0L)
                        .price(50000)
                        .status(SeatStatus.RESERVED)
                        .build(),
                Seat.builder()
                        .seatNum(5L)
                        .concertTimeId(2L)
//                        .concertHallId(0L)
                        .price(50000)
                        .status(SeatStatus.RESERVED)
                        .build()
        );

        List<ConcertTime> 나훈아_concertTimeList = List.of(
                ConcertTime.builder()
                        .time(ConcertTime.fromStr("2024/04/20/ 17:00:00 KST"))
//                        .seats(전좌석예약가능)
                        .concertId(1L)
//                        .concertHallId(0L)
//                        .maxSeatNum(50)
//                        .currAvailableSeatNum(0)
                        .build(),
                ConcertTime.builder()
                        .time(ConcertTime.fromStr("2024/04/27/ 17:00:00 KST"))
//                        .seats(전좌석_매진)
                        .concertId(1L) // TODO: 어떻게 넣을 때 연관된 concertID를 인식하고 넣지?
//                        .concertHallId(0L)
//                        .maxSeatNum(50)
//                        .currAvailableSeatNum(0)
                        .build()
        );


        Concert concert = Concert.builder()
                .name("나훈아50주년콘서트")
//                .concertTimeList(나훈아_concertTimeList)
                .build();


        concertRepositoryImpl.saveConcert(concert);

    }

    public void settingPointInfo(){
        User 신은성 = User.builder()
                        .id(1L)
                        .balance(30000)
                        .build();

        PointHistory 충전_20000 = PointHistory.builder()
                                            .userId(신은성.getId())
                                            .amount(20000)
                                            .pointType(PointType.CHARGE)
                                            .build();

        PointHistory 사용_50000 = PointHistory.builder()
                .userId(신은성.getId())
                .amount(20000)
                .pointType(PointType.USE)
                .build();

        PointHistory 사용_9000000 = PointHistory.builder()
                .userId(신은성.getId())
                .amount(9000000)
                .pointType(PointType.USE)
                .build();

        pointRepositoryImpl.saveUser(신은성);
        pointRepositoryImpl.saveUser(신은성);
//        pointRepositoryImpl.savePointHistory(충전_20000);
//        pointRepositoryImpl.savePointHistory(사용_50000);
//        pointRepositoryImpl.savePointHistory(사용_9000000);
    }


}
