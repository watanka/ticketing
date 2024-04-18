package com.project1.ticketing.domain.concert.infrastructure;

import com.project1.ticketing.domain.concert.models.Concert;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ConcertJpaRepository {

    @PersistenceContext
    private EntityManager em;

    List<Concert> getAll(){
        Query query = em.createQuery("SELECT * FROM Concert");
        return query.getResultList();
    };
    Optional<Concert> findById(long concertId){
        return Optional.of(em.find(Concert.class, concertId));
    };


}
