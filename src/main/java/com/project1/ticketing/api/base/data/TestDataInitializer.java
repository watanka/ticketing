package com.project1.ticketing.api.base.data;


import com.project1.ticketing.domain.concert.components.ConcertService;
import com.project1.ticketing.domain.concert.repository.ConcertCoreRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestDataInitializer {

    private final ConcertService concertService;

    @PostConstruct
    public void init(){

        List<String> concertNameList = List.of(
                "아일릿 콘서트",
                "뉴진스 콘서트",
                "하니 단독 콘서트",
                "페이커 앞구르기 콘서트",
                "신은성 장기자랑 콘서트",
                "전국노래자랑 송파편",
                "나훈아 60주년 콘서트",
                "판타지 오케스트라의 밤",
                "미야자키 하야오 OST 콘서트" ,
                "별빛 아래 클래식" ,
                "영화 음악 대향연" ,
                "추억의 90년대 가요 콘서트" ,
                "디즈니 매직 멜로디" ,
                "소울풀 재즈 나이트" ,
                "오페라의 유령 갈라쇼" ,
                "신나는 록 페스티벌" ,
                "드라마 명장면 OST 콘서트" ,
                "세계 민속음악 축제" ,
                "힐링 포크 음악회" ,
                "전자 음악의 신세계" ,
                "크리스마스 캐럴 콘서트" ,
                "애니메이션 주제가 콘서트" ,
                "꿈의 발라드 콘서트" ,
                "청춘의 인디 음악 페스티벌" ,
                "로맨틱 팝 콘서트" ,
                "뮤지컬 히트곡 콘서트" ,
                "클래식 황금명곡" ,
                "세계 음악 여행 콘서트" ,
                "소리와 빛의 향연" ,
                "환상의 EDM 파티" ,
                "블루스 & 재즈 나이트" ,
                "피아노의 밤" ,
                "가을 감성 어쿠스틱" ,
                "록큰롤 전설 콘서트" ,
                "지브리 명작 OST" ,
                "레트로 디스코 나이트" ,
                "우주 판타지 콘서트" ,
                "마법의 하모니" ,
                "클래식 인 더 파크"

        );

        List<ZonedDateTime> ConcertTimeList


        concertService.registerConcert();

        concertService.registerConcertTime();

        concertService.registerSeat();

    }
}
