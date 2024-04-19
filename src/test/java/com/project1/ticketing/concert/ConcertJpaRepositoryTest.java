package com.project1.ticketing.concert;

import com.project1.ticketing.domain.concert.infrastructure.ConcertJpaRepository;
import com.project1.ticketing.domain.concert.infrastructure.ConcertRepository;
import com.project1.ticketing.domain.concert.models.Concert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class ConcertJpaRepositoryTest {
    @Autowired
    ConcertJpaRepository concertJpaRepository;
    @Autowired
    ConcertRepository concertRepository;

    @Test
    void testJpaRepositoryWorking(){
        Concert concert = new Concert("나훈아50주년콘서트");
        Concert savedConcert = concertJpaRepository.save(concert);
        Concert foundConcert = concertJpaRepository.findById(savedConcert.getId());

        Assertions.assertThat(foundConcert.getId()).isEqualTo(concert.getId());
        Assertions.assertThat(foundConcert.getName()).isEqualTo(concert.getName());
    }


    @Test
    void testSpringJpaRepositoryWorking(){
        Concert concert = new Concert("나훈아50주년콘서트");
        Concert savedConcert = concertRepository.save(concert);
        Concert foundConcert = concertRepository.findById(savedConcert.getId()).get();

        assertThat(foundConcert.getId()).isEqualTo(concert.getId());
        assertThat(foundConcert.getName()).isEqualTo(concert.getName());
        assertThat(concert).isEqualTo(foundConcert);
    }


}
