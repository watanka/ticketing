package com.project1.ticketing.domain.point.repository;


import com.project1.ticketing.domain.point.models.User;

public interface UserRepository {
    User getById(long userId);
}
