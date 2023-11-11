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

@Component // 스프링 컴포넌트로 등록
public class JwtTokenProvider {

    @Value("${jwt.key}")
    private String secretKey; // JWT 비밀키

    @Value("${jwt.accessTokenExpirationTime}")
    private Long accessTokenExpirationTime; // 엑세스 토큰 만료 시간

    // 리프레시 토큰 만료 시간은 현재 사용하지 않음
//    @Value("${jwt.refreshTokenExpirationTime}")
//    private Long refreshTokenExpirationTime;

    // 사용자의 이메일로부터 엑세스 토큰 생성
    public String generateAccessToken(String email) {
        return generateToken(email, accessTokenExpirationTime);
    }

    // 실제 토큰 생성 로직
    private String generateToken(String email, Long expirationTime) {
        Date now = new Date(); // 현재 시간
        Date expireTime = new Date(now.getTime() + expirationTime); // 만료 시간 설정
        return Jwts.builder()
                .setSubject(email) // 토큰 주제 설정
                .setIssuedAt(now) // 발급 시간
                .setExpiration(expireTime) // 만료 시간
                .signWith(generateKey(secretKey), SignatureAlgorithm.HS512) // 서명 알고리즘과 키 설정
                .compact();
    }

    // 비밀키를 바탕으로 Key 객체 생성
    private Key generateKey(String secret) {
        byte[] accessKeyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(accessKeyBytes);
    }

    // 리프레시 토큰 생성 로직은 현재 사용하지 않음
//    public String generateRefreshToken() {
//        return generateToken(UUID.randomUUID().toString(), refreshTokenExpirationTime);
//    }

    // 토큰의 유효성 검사
    public void validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            // 만료된 토큰 예외 처리
            throw new RuntimeException("토큰이 만료됨!");
        } catch (JwtException e) {
            // 기타 JWT 예외 처리
            throw new RuntimeException("유효하지 않은 토큰임!");
        }
    }

    // 엑세스 토큰에서 사용자의 이메일 추출
    public String extractAccessToken(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            // 만료된 토큰 예외 처리
            throw new RuntimeException("토큰이 만료됨!");
        } catch (JwtException e) {
            // 기타 JWT 예외 처리
            throw new RuntimeException("유효하지 않은 토큰임!");
        }
    }

}
