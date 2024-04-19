package com.project1.ticketing.domain.concert.repository;

import com.project1.ticketing.domain.concert.models.Concert;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConcertJpaRepository extends JpaRepository<Concert, Long> {
}