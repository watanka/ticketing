package com.project1.ticketing.domain.point.components;

import com.project1.ticketing.domain.point.models.User;
import com.project1.ticketing.domain.point.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserManager {
    UserJpaRepository userRepository;

    @Autowired
    public UserManager(UserJpaRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findById(long userId){
        return userRepository.findById(userId);
    }

    public User save(User user){
        return userRepository.save(user);
    }

}
