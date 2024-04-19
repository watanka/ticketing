package com.project1.ticketing.concert;

import com.project1.ticketing.domain.concert.repository.ConcertJpaRepository;
import com.project1.ticketing.domain.concert.infrastructure.ConcertRepositoryImpl;
import com.project1.ticketing.domain.concert.infrastructure.ConcertTimeRepositoryImpl;
import com.project1.ticketing.domain.concert.infrastructure.SeatRepositoryImpl;
import com.project1.ticketing.domain.concert.models.Concert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
public class ConcertJpaRepositoryTest {
    @Autowired
    ConcertJpaRepository concertJpaRepository;
    @Autowired
    ConcertRepositoryImpl concertRepositoryImpl;
    @Autowired
    ConcertTimeRepositoryImpl concertTimeRepositoryImpl;

    @Autowired
    SeatRepositoryImpl seatRepositoryImpl;

    @Autowired
    TestDataHandler testDataHandler;



    @BeforeEach
    void setUp(){
        testDataHandler.settingConcertInfo();
    }

    @Test
    void testJpaRepositoryWorking(){
        Concert concert = Concert.builder().name("나훈아50주년콘서트1").build();
        Concert savedConcert = concertJpaRepository.save(concert);
        Concert foundConcert = concertJpaRepository.findById(savedConcert.getId());

        Assertions.assertThat(foundConcert.getId()).isEqualTo(concert.getId());
        Assertions.assertThat(foundConcert.getName()).isEqualTo(concert.getName());
    }


    @Test
    void testSpringJpaRepositoryWorking(){
        Concert concert = Concert.builder().name("나훈아50주년콘서트2").build();
        Concert savedConcert = concertRepositoryImpl.save(concert);
        Concert foundConcert = concertRepositoryImpl.findConcertById(savedConcert.getId()).get();

        assertThat(foundConcert.getId()).isEqualTo(concert.getId());
        assertThat(foundConcert.getName()).isEqualTo(concert.getName());
        assertThat(concert).isEqualTo(foundConcert);
    }


    @Test
    void testGetAvailableConcertDate(){


        }





}
