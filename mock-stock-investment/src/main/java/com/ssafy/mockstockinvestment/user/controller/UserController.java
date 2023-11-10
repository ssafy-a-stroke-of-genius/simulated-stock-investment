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

@RestController // 이 클래스가 REST 컨트롤러임을 나타냄
@AllArgsConstructor // Lombok을 사용하여 모든 필드에 대한 생성자 자동 생성
@RequestMapping("/auth") // 이 컨트롤러의 모든 메소드는 "/auth" 경로로 매핑됨
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register") // "/auth/register" 경로로 POST 요청을 처리
    public ResponseEntity<String> register(@RequestBody CreateUserRequest createUserRequest) {
        String createdUserId = userService.register(createUserRequest);
        // 생성된 유저 ID로 URI를 만들어 응답으로 보냄
        return ResponseEntity.created(URI.create("/auth/register" + createdUserId)).build();
    }

    @PostMapping("/login") // "/auth/login" 경로로 POST 요청을 처리
    public ResponseEntity<TokenResponse> login(@RequestParam LoginRequest loginRequest) {
        // 로그인 요청을 처리하여 토큰 응답을 반환
        TokenResponse tokenResponse = userService.login(loginRequest);
        return ResponseEntity.ok(tokenResponse);
    }

    @GetMapping // "/auth" 경로로 GET 요청을 처리
    public ResponseEntity<Void> validateToken(HttpServletRequest httpServletRequest) {
        // 요청에서 Authorization 헤더를 가져옴
        String token = httpServletRequest.getHeader("Authorization");
        if (token == null) {
            // 토큰이 없으면 예외 발생
            throw new RuntimeException("헤더에 토큰이 없엉ㅠㅠ");
        }
        // 토큰 유효성 검사 수행
        jwtTokenProvider.validateToken(token);
        // 검사가 성공하면 200 OK 응답 반환
        return ResponseEntity.ok().build();
    }

    // 리프레시 토큰이 없으면 갱신 로직이 필요 없음
    // 아래 주석 처리된 메소드는 토큰 갱신 로직을 구현한 것이지만, 현재는 사용하지 않음
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
//        return ResponseEntity.ok(accessToken);
//    }

}
