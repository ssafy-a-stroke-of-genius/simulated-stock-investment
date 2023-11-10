package com.ssafy.mockstockinvestment.user.dto.response;

public class TokenResponse {

    private final String accessToken;
//    private final String refreshToken;

    public TokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }

}