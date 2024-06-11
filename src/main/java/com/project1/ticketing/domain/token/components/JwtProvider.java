package com.project1.ticketing.domain.token.components;

import com.project1.ticketing.api.utils.exceptions.InvalidTokenException;
import com.project1.ticketing.api.utils.response.BaseResponseStatus;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtProvider {
    @Value("${spring.application.name}")
    private String issuer;

    @Value("${spring.jwt.access-expiration}")
    private Long accessExpiration;

    private final SecretKey secretKey;

    public JwtProvider(@Value("${service.jwt.secret-key}") String secretKey){

        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
    }

    public Claims getClaims(String jwtToken){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();

    }

    public String create(long userId, Date issuedAt){
        return Jwts.builder()
                .claim("userId", String.valueOf(userId))
                .setIssuer(issuer)
                .setIssuedAt(issuedAt)
                .setExpiration(new Date(issuedAt.getTime() + accessExpiration))
                .signWith(secretKey, Jwts.SIG.HS512)
                .compact();
    }

    public Jws<Claims> verifyToken(String jwtToken){
        try{
            return Jwts.parser().verifyWith(secretKey).build()
                    .parseSignedClaims(jwtToken);

        }catch (Exception e){
            throw new InvalidTokenException(BaseResponseStatus.FAIL);
        }
    }

}
