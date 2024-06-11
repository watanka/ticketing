package com.project1.ticketing.domain.token.models;


//import com.project1.ticketing.domain.token.components.TokenService;


import jakarta.annotation.Nullable;

public record Token(String jwt, long waitingNum){

}
