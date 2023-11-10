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

    private final JwtTokenProvider jwtTokenProvider;
    private final ManagerRepository managerRepository;
    private final StudentRepository studentRepository;

    public String register(CreateUserRequest createUserRequest) {
        Optional<Manager> managerResult = managerRepository.findByEmail(createUserRequest.getEmail());
        Optional<Student> studentResult = studentRepository.findByEmail(createUserRequest.getEmail());
        if (managerResult.isPresent() || studentResult.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 메일!");
        }

        if (createUserRequest.getTeacher()) {
            Manager manager = new Manager();
            manager.setEmail(createUserRequest.getEmail());
            // 비밀번호 암호화 하면 좋아용 ^.^
            manager.setUserPassword(createUserRequest.getPassword());
            manager.setUserRole(UserEnum.관리자);
            Manager savedManager = managerRepository.save(manager);
            return savedManager.getUserId();
        } else {
            Student student = new Student();
            student.setEmail(createUserRequest.getEmail());
            student.setUserPassword(createUserRequest.getPassword());
            student.setUserRole(UserEnum.학생);
            Student savedStudent = studentRepository.save(student);
            return savedStudent.getUserId();
        }
    }

    public TokenResponse login(LoginRequest loginRequest) {
        // 대충 일치하는지 검증하는 로직
        Optional<Manager> managerResult = managerRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        Optional<Student> studentResult = studentRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        if (managerResult.isEmpty() && studentResult.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않습니다");
        }

        String accessToken = jwtTokenProvider.generateAccessToken(loginRequest.getEmail());
//        String refreshToken = jwtTokenProvider.generateRefreshToken();

        return new TokenResponse(accessToken);
    }





}