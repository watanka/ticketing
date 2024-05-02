package com.project1.ticketing.domain.point.repository;

import com.project1.ticketing.domain.point.models.User;
import jakarta.persistence.LockModeType;
import org.hibernate.LockMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.hibernate.LockMode.PESSIMISTIC_WRITE;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    User save(User user);

    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<User> findById(long userId);

}
