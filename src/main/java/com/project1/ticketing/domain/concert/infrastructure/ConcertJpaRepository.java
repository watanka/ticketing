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

//    public List<Concert> getAll(){
//        Query query = em.createQuery("SELECT * FROM concert;");
//        return query.getResultList();
//    };
    public Concert findById(long concertId){
        return em.find(Concert.class, concertId);
    };

    public Concert save(Concert concert){
        em.persist(concert);
        return concert;

    }

}
