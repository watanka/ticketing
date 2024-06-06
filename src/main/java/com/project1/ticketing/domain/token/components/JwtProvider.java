package com.project1.ticketing.domain.token.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {
    @Value("${spring.application.name}")
    private String issuer;

    @Value("${spring.jwt.access-expiration}")
    private Long accessExpiration;

    private final SecretKey secretKey;

    public JwtProvider(@Value("${service.jwt.secret-key}") String secretKey){

        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
    }

    public String createAccessToken(JwtPayload jwtPayload){
        return Jwts.builder()
                .claim("waiting-number", jwtPayload)
                .issuer(issuer)
                .issuedAt(jwtPayload.issuedAt())
                .expiration(new Date(jwtPayload.issuedAt().getTime() + accessExpiration))
                .signWith(secretKey, Jwts.SIG.HS512)
                .compact();
    }

    public JwtPayload verifyToken(String jwtToken){
        Jws<Claims> claimJws = Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(jwtToken);

        return new JwtPayload(claimJws.getPayload().get(jwtPropertiesProvider.getEmailKey(), String.class), claimJws.getPayload().getIssuedAt());
    }
}
