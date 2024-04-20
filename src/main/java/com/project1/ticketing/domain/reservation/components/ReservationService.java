package com.project1.ticketing.domain.reservation.components;

import com.project1.ticketing.api.dto.request.ReservationRequest;
import com.project1.ticketing.api.dto.response.ReservationResponse;
import com.project1.ticketing.domain.reservation.models.Reservation;
import com.project1.ticketing.domain.reservation.models.ReservationStatus;
import com.project1.ticketing.domain.reservation.repository.ReservationCoreRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public class ReservationService implements IReservationService {

    ReservationCoreRepository reservationRepository;
    ReservationValidator reservationValidator;

    public ReservationService(ReservationCoreRepository reservationRepository, ReservationValidator reservationValidator) {
        this.reservationRepository = reservationRepository;
        this.reservationValidator = reservationValidator;
    }

    public ReservationResponse register(ReservationRequest request){

        long userId = request.getUserId();
        long seatId = request.getSeatId();
        String concertTIme = request.getConcertTime();


        // reservation 검증
        // 좌석 예약 가능 확인
        // 예약 생성
        // 예약 저장 reservationRepository.save
        // 좌석 상태 변경
        // 좌석 상태 저장 seatRepository.save
        // 예약 및 좌석 상태 관리 이벤트 발행
        reservationValidator.validateSeat(seatId);

        Reservation reservation = Reservation.builder()
                .userId(userId)
                .concertTime(ZonedDateTime.parse(concertTIme))
                .seatId(seatId)
                .status(ReservationStatus.TEMPORARY)
                .createAt(ZonedDateTime.now())
                .build();

        reservationRepository.save(reservation);

        return ReservationResponse.from(reservation);
    }

    public Reservation updateReservationStatus(long reservationId){

        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if (reservation.isEmpty()){
            throw new RuntimeException("예약을 찾을 수 없습니다.");
        }
        Reservation selectedReservation = reservation.get();

        ZonedDateTime reservationExpiredTime = selectedReservation.getCreateAt().plusMinutes(5);


        if (ZonedDateTime.now().isAfter(reservationExpiredTime)){
            selectedReservation.setStatus(ReservationStatus.CANCELLED);
            reservationRepository.save(selectedReservation);
        }
        return selectedReservation;
    }

    public List<Reservation> findAllByUserId(long userId){
        return reservationRepository.findAllByUserId(userId);
    }

    public Optional<Reservation> findByReservationId(long reservationId){
        return reservationRepository.findById(reservationId);
    }

    @Override
    public List<ReservationResponse> checkReservationList(long userId) {
        // userId 검증

        return null;
    }

    @Override
    public ReservationResponse check(long userId, long reservationId) {
        // userId 검증
        // reservationId 검증

        return null;
    }
}
