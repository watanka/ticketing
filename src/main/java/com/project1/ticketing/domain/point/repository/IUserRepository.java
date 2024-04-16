package com.project1.ticketing.domain.point.repository;


import com.project1.ticketing.domain.point.models.User;

public interface IUserRepository {
    User getById(long userId);
    User update(User user);
}
