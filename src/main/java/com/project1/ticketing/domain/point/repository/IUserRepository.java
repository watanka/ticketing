package com.project1.ticketing.domain.point.repository;


import com.project1.ticketing.domain.point.models.User;

import java.util.Optional;

public interface IUserRepository {
    Optional<User> findById(long userId);
    User save(User user);
}
