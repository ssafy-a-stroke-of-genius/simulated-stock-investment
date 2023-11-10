package com.ssafy.mockstockinvestment.user.domain;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    @Value("${jwt.key}")
    private String secretKey; // 비밀키~

    @Value("${jwt.accessTokenExpirationTime}")
    private Long accessTokenExpirationTime; // 엑세스 토큰 유효기간

//    @Value("${jwt.refreshTokenExpirationTime}")
//    private Long refreshTokenExpirationTime; // 리프레시 토큰 유효기간

    public String generateAccessToken(String email) {
        return generateToken(email, accessTokenExpirationTime);
    }

    private String generateToken(String email, Long expirationTime) {
        Date now = new Date(); // 현재시간
        Date expireTime = new Date(now.getTime() + expirationTime); // 유통기한
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expireTime)
                .signWith(generateKey(secretKey), SignatureAlgorithm.HS512)
                .compact();
    }

    private Key generateKey(String secret) {
        byte[] accessKeyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(accessKeyBytes);
    }

//    public String generateRefreshToken() {
//        return generateToken(UUID.randomUUID().toString(), refreshTokenExpirationTime);
//    }

    public void validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("토큰이 만료됨!");
        } catch (JwtException e) {
            throw new RuntimeException("유효하지 않은 토큰임!");
        }
    }

    public String extractAccessToken(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("토큰이 만료됨!");
        } catch (JwtException e) {
            throw new RuntimeException("유효하지 않은 토큰임!");
        }
    }

}