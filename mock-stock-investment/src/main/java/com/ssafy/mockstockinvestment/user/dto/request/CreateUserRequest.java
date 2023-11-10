package com.ssafy.mockstockinvestment.user.dto.request;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

public class CreateUserRequest {

    @NotNull
    private final String email;

    @NotNull
    private final String password;

    @NotNull
    private final String passwordCheck;

    @Nullable
    private final Boolean isManager;

    public CreateUserRequest(String email, String password, String passwordCheck, Boolean isManager) {
        if (!password.equals(passwordCheck)) {
            throw new IllegalArgumentException("비밀번호와 비밀번호 확인이 일치하지 않음");
        }
        this.email = email;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.isManager = isManager;

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public Boolean getTeacher() {
        return isManager;
    }
}