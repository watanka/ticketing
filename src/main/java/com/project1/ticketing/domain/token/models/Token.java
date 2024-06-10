package com.project1.ticketing.domain.token.models;


//import com.project1.ticketing.domain.token.components.TokenService;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public record Token(
        Date issuedAt,
        Map<String, Object> claim

        // JWT claim에 들어갈 내용

){

    public Token(Date issuedAt, Map<String, Object> claim) {
        this.issuedAt = issuedAt;
        this.claim = claim;
    }

}
