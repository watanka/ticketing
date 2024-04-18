package com.project1.ticketing.domain.point.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

//@Entity
@Getter
public class User{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    long balance;

    public void usePoint(long amount){
        this.balance -= amount;
    }

    public void chargePoint(long amount){
        this.balance += amount;
    }

    @Builder
    public User(long id, long balance) {
        this.id = id;
        this.balance = balance;
    }
}
