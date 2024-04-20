package com.project1.ticketing.domain.point.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
@Table(name="users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    long balance;

    public void usePoint(long amount){
        this.balance -= amount;
    }

    public void chargePoint(long amount){this.balance += amount;
    }


}
