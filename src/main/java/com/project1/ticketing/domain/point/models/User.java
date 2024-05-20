package com.project1.ticketing.domain.point.models;

import com.project1.ticketing.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Entity
@Getter
@Builder
@Table(name="users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Builder.Default
    long balance = 0;

    public User(long balance){
        this.balance = balance;
    }

    public User(long id, long balance) {
        this.id = id;
        this.balance = balance;
    }

    protected User(){};

    public void usePoint(long amount){
        this.balance -= amount;
    }

    public void chargePoint(long amount){this.balance += amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && balance == user.balance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance);
    }
}


