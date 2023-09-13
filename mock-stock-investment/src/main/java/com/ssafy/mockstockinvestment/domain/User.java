package com.ssafy.mockstockinvestment.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

//사용자
@Entity //DB 테이블
public class User {
    @Id //식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ID값 자동으로 올라가게 설정
    @Column(name="id")
    private int id;

    @Column(name="class_id") //사용자 학급
    private int classId;

    @Column(name="email", length = 40) //사용자 이메일
    @NotNull //널 허용 X
    private String email;

    @Column(name="password", length = 20) //사용자 비밀번호
    @NotNull //널 허용 X
    private String password;

    @Column(name="real_name", length = 10) //사용자 본명
    @NotNull //널 허용 X
    private String realName;

    @Column(name="nickname", length = 10) //사용자 별명
    private String nickName;

    @Column(name="role", length = 10) //사용자 역할(교사, 학생)
    @NotNull //널 허용 X
    private String role;

    @Column(name="status", length = 10) //사용자 계정 상태
    @NotNull //널 허용 X
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
