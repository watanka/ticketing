package com.project1.ticketing.domain.point.repository;

import com.project1.ticketing.domain.point.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
    User save(User user);
    Optional<User> findById(long userId);

}
