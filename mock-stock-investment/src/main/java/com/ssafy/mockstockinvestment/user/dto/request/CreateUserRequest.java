package com.ssafy.mockstockinvestment.user.dto.request;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

public class CreateUserRequest {

    @NotNull
    private final String email; // 사용자 이메일, Null 허용하지 않음

    @NotNull
    private final String password; // 사용자 비밀번호, Null 허용하지 않음

    @NotNull
    private final String passwordCheck; // 비밀번호 확인용 필드, Null 허용하지 않음

    @Nullable
    private final Boolean isManager; // 관리자 여부, Null 허용

    // 생성자: 이메일, 비밀번호, 비밀번호 확인, 관리자 여부를 받아 객체를 생성
    public CreateUserRequest(String email, String password, String passwordCheck, Boolean isManager) {
        // 비밀번호와 비밀번호 확인이 일치하지 않으면 예외 발생
        if (!password.equals(passwordCheck)) {
            throw new IllegalArgumentException("비밀번호와 비밀번호 확인이 일치하지 않음");
        }
        this.email = email;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.isManager = isManager;
    }

    // 이메일 getter 메소드
    public String getEmail() {
        return email;
    }

    // 비밀번호 getter 메소드
    public String getPassword() {
        return password;
    }

    // 비밀번호 확인 getter 메소드
    public String getPasswordCheck() {
        return passwordCheck;
    }

    // 관리자 여부 getter 메소드
    public Boolean getTeacher() {
        return isManager;
    }
}
