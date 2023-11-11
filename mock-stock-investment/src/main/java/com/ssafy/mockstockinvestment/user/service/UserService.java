package com.ssafy.mockstockinvestment.user.service;

import java.util.Optional;

import com.ssafy.mockstockinvestment.user.domain.JwtTokenProvider;
import com.ssafy.mockstockinvestment.user.domain.Manager;
import com.ssafy.mockstockinvestment.user.domain.Student;
import com.ssafy.mockstockinvestment.user.domain.UserEnum;
import com.ssafy.mockstockinvestment.user.domain.repository.ManagerRepository;
import com.ssafy.mockstockinvestment.user.domain.repository.StudentRepository;
import com.ssafy.mockstockinvestment.user.dto.request.CreateUserRequest;
import com.ssafy.mockstockinvestment.user.dto.request.LoginRequest;
import com.ssafy.mockstockinvestment.user.dto.response.TokenResponse;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final JwtTokenProvider jwtTokenProvider; // JWT 토큰 제공자
    private final ManagerRepository managerRepository; // 관리자 저장소
    private final StudentRepository studentRepository; // 학생 저장소

    // 사용자 등록 메소드
    public String register(CreateUserRequest createUserRequest) {
        // 이메일로 기존 사용자 존재 여부 확인
        Optional<Manager> managerResult = managerRepository.findByEmail(createUserRequest.getEmail());
        Optional<Student> studentResult = studentRepository.findByEmail(createUserRequest.getEmail());
        if (managerResult.isPresent() || studentResult.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 메일!");
        }

        // 관리자로 등록하는 경우
        if (createUserRequest.getTeacher()) {
            Manager manager = new Manager();
            manager.setEmail(createUserRequest.getEmail());
            // 비밀번호 암호화 추가 필요
            manager.setUserPassword(createUserRequest.getPassword());
            manager.setUserRole(UserEnum.관리자);
            Manager savedManager = managerRepository.save(manager);
            return savedManager.getUserId();
        } else {
            // 학생으로 등록하는 경우
            Student student = new Student();
            student.setEmail(createUserRequest.getEmail());
            student.setUserPassword(createUserRequest.getPassword());
            student.setUserRole(UserEnum.학생);
            Student savedStudent = studentRepository.save(student);
            return savedStudent.getUserId();
        }
    }

    // 로그인 메소드
    public TokenResponse login(LoginRequest loginRequest) {
        // 이메일과 비밀번호 일치 여부 확인
        Optional<Manager> managerResult = managerRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        Optional<Student> studentResult = studentRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        if (managerResult.isEmpty() && studentResult.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않습니다");
        }

        // JWT 액세스 토큰 생성
        String accessToken = jwtTokenProvider.generateAccessToken(loginRequest.getEmail());
        // 지금은 사용하지 않으므로 RefreshToken 주석처리
//        String refreshToken = jwtTokenProvider.generateRefreshToken();

        return new TokenResponse(accessToken);
    }





}