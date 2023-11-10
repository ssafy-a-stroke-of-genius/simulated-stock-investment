package com.ssafy.mockstockinvestment.user.controller;

import java.net.URI;

import com.ssafy.mockstockinvestment.user.domain.JwtTokenProvider;
import com.ssafy.mockstockinvestment.user.dto.request.CreateUserRequest;
import com.ssafy.mockstockinvestment.user.dto.request.LoginRequest;
import com.ssafy.mockstockinvestment.user.dto.response.TokenResponse;
import com.ssafy.mockstockinvestment.user.service.UserService;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody CreateUserRequest createUserRequest) {
        String createdUserId = userService.register(createUserRequest);
        return ResponseEntity.created(URI.create("/auth/register" + createdUserId)).build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestParam LoginRequest loginRequest) {
        TokenResponse tokenResponse = userService.login(loginRequest);
        return ResponseEntity.ok(tokenResponse);
    }

    @GetMapping
    public ResponseEntity<Void> validateToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if (token == null) {
            throw new RuntimeException("헤더에 토큰이 없엉ㅠㅠ");
        }
        jwtTokenProvider.validateToken(token);
        return ResponseEntity.ok().build(); // HTTP Response Sign 200 -> 성공했다 // 404, 403 같은 것과 같은 결임
    }

    // 리프레시 토큰 없을때는 갱신 로직 필요없음
//    @PostMapping("/renew")
//    public ResponseEntity<String> renewToken(HttpServletRequest httpServletRequest) {
//        String token = httpServletRequest.getHeader("Authorization");
//        if (token == null) {
//            throw new RuntimeException("헤더에 토큰이 없엉ㅠㅠ");
//        }
//
//        // 갱신시는 이미 토큰 만료됐으니까 유효성 검사는 안함
//        String email = jwtTokenProvider.extractAccessToken(token);
//        String accessToken = jwtTokenProvider.generateAccessToken(email);
//
//        return ResponseEntity.ok(accessToken); // HTTP Response Sign 200 -> 성공했다 // 404, 403 같은 것과 같은 결임
//    }

}