package com.ssafy.mockstockinvestment.user.dto.response;

public class TokenResponse {

    private final String accessToken; // 액세스 토큰

    // private final String refreshToken; // 리프레시 토큰, 현재 사용하지 않음

    // 생성자: 액세스 토큰을 받아 객체를 생성
    public TokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }

}
