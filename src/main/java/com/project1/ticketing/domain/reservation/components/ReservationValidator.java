package com.project1.ticketing.domain.reservation.components;

import com.project1.ticketing.api.dto.response.SeatResponse;
import com.project1.ticketing.domain.concert.models.SeatStatus;
import com.project1.ticketing.domain.concert.repository.SeatJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ReservationValidator {

    SeatJpaRepository seatRepository;

    @Autowired
    public ReservationValidator(SeatJpaRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public void validateSeat(long seatId){
        if (!seatRepository.findByIdIsStatus(seatId, SeatStatus.AVAILABLE)){
            throw new RuntimeException("해당 좌석은 예약이 불가능합니다.");
        };

        // 사용자 ID 없을 때


        // 콘서트 ID 없을 때

        // 좌석이 예약 불가능상태일 때

    }


}
