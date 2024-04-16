package com.project1.ticketing.domain.point.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private long point;


    public void usePoint(long amount){
        this.point -= amount;
    }
    public void chargePoint(long amount){
        this.point += amount;
    }

}
