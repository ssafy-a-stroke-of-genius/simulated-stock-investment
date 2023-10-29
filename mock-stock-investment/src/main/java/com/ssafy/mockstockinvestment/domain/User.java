package com.ssafy.mockstockinvestment.domain;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

//사용자
@Entity
@Table(name = "users") //DB 테이블
@Data //getter, setter, 기본 생성자, toString() 메서드
public class User {
    @Id //식별자
    @Column(name="user_id", length = 30)
    private String userId;

    @Column(name="user_role", length = 7) //사용자 역할
    @NotNull
    private String userRole;

    @Column(name="password", length = 30) //사용자 비밀번호
    @NotNull
    private String userPassword;

    @Column(name="user_name", length = 20) //사용자 이름
    @NotNull
    private String userName;

    @Column(name="profile_img", length = 100) //사용자 프로필 이미지
    @NotNull
    private String profileImg;
}
