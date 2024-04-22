package com.project1.ticketing.domain.point.repository;


import com.project1.ticketing.domain.point.models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> findById(long userId);
    User save(User user);

    void deleteAll();
}
