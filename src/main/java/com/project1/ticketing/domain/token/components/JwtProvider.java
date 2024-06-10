package com.project1.ticketing.domain.token.components;

import com.project1.ticketing.domain.token.models.Token;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.Claims;
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

    private Claims generateClaims(long userId){
        Claims claims = new DefaultClaims() {
        };
        claims.put("sub", String.valueOf(userId));
        claims.put("created", new Date());
        claims.put("issuedAt", new Date());
        return claims;

    }

//    private Claims getClaimsFromToken(String token){
//        return Jwts.parser()
//                .setSigningKey(secretKey)
//                .(token)
//                .getBody();
//    }

    public String create(long userId, Date issuedAt){
        Claims claims = generateClaims(userId);
        return Jwts.builder()
                .setClaims(claims)
                .issuer(issuer)
                .issuedAt(issuedAt)
                .expiration(new Date(issuedAt.getTime() + accessExpiration))
                .signWith(secretKey, Jwts.SIG.HS512)
                .compact();
    }

    public Jws<Claims> verifyToken(String jwtToken){
        return Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(jwtToken);
    }

}
