package com.project1.ticketing.api.base.data;


import com.project1.ticketing.domain.concert.components.ConcertService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class TestDataInitializer {

    private final ConcertService concertService;

    @PostConstruct
    public void init(){

        List<String> concertNameList = List.of(
                "아일릿 콘서트", "뉴진스 콘서트", "하니 단독 콘서트", "페이커 앞구르기 콘서트", "신은성 장기자랑 콘서트",
                "전국노래자랑 송파편", "나훈아 60주년 콘서트", "판타지 오케스트라의 밤", "미야자키 하야오 OST 콘서트", "별빛 아래 클래식",
                "영화 음악 대향연", "추억의 90년대 가요 콘서트", "디즈니 매직 멜로디", "소울풀 재즈 나이트", "오페라의 유령 갈라쇼",
                "신나는 록 페스티벌", "드라마 명장면 OST 콘서트", "세계 민속음악 축제", "힐링 포크 음악회", "전자 음악의 신세계",
                "크리스마스 캐럴 콘서트", "애니메이션 주제가 콘서트", "꿈의 발라드 콘서트", "청춘의 인디 음악 페스티벌", "로맨틱 팝 콘서트",
                "뮤지컬 히트곡 콘서트", "클래식 황금명곡", "세계 음악 여행 콘서트", "소리와 빛의 향연", "환상의 EDM 파티",
                "블루스 & 재즈 나이트", "피아노의 밤", "가을 감성 어쿠스틱", "록큰롤 전설 콘서트", "지브리 명작 OST",
                "레트로 디스코 나이트", "봄날의 팝 콘서트", "여름밤의 재즈", "가을밤의 클래식", "겨울왕국 OST",
                "한국 전통 음악회", "세계 팝 음악 페스티벌", "유로 댄스 나이트", "프랑스 샹송의 밤", "브라질 삼바 축제",
                "북유럽 포크 음악", "아프리카 리듬의 밤", "일본 전통 음악회", "중국 고전 음악의 밤"

        );

        List<ZonedDateTime> ConcertTimeList = generateTimeList(); // 하루에 두번(2시,7시), 금토일, 4주, 6개월; => 2 * 3 * 4 * 6 = 144

        List<Integer> SeatList = IntStream.rangeClosed(1, 10)
                                .boxed()
                                .toList();

        int concertId = 0;
        int concertTimeId = 0;
        int seatNum = 0;


        // #콘서트(50) * #콘서트시간(144) * #(seat 10) = 7200
        for (String concert : concertNameList) {
            concertId++;
            concertService.registerConcert(concert);
            for (ZonedDateTime concertTime : ConcertTimeList) {
                concertTimeId++;
                concertService.registerConcertTime(concertTime, concertTimeId);
                for (Integer seat : SeatList) {
                    concertService.registerSeat(seatNum, concertTimeId, 200000);

                }
            }
        }







    }


    List<ZonedDateTime> generateTimeList(){
        List<ZonedDateTime> dateTimes = new ArrayList<>();
        ZonedDateTime startDate = ZonedDateTime.now();
        ZonedDateTime endDate = startDate.plusMonths(6);

        // Time instances for 2 PM and 7 PM
        LocalTime time2PM = LocalTime.of(14, 0);
        LocalTime time7PM = LocalTime.of(19, 0);

        // Adjust to the first upcoming Friday
        ZonedDateTime current = startDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY)).with(time2PM);

        while (current.isBefore(endDate)) {
            for (DayOfWeek day : new DayOfWeek[] { DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY }) {
                ZonedDateTime dayStart2PM = current.with(TemporalAdjusters.nextOrSame(day)).with(time2PM);
                ZonedDateTime dayStart7PM = current.with(TemporalAdjusters.nextOrSame(day)).with(time7PM);

                if (dayStart2PM.isBefore(endDate)) {
                    dateTimes.add(dayStart2PM);
                }

                if (dayStart7PM.isBefore(endDate)) {
                    dateTimes.add(dayStart7PM);
                }
            }
            current = current.plusWeeks(1).with(DayOfWeek.FRIDAY).with(time2PM);
        }

        return dateTimes;

    }

}
