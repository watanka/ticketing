package com.project1.ticketing.domain.point.repository;

import com.project1.ticketing.domain.point.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    User save(User user);
    Optional<User> findById(long userId);

}
