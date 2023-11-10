package com.ssafy.mockstockinvestment.user.domain;

import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

//사용자
@Entity(name = "users") //DB 테이블
@Data //getter, setter, 기본 생성자, toString() 메서드
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //한 테이블에 자식 개체들의 모든 칼럼이 들어있음
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorColumn(name = "user_role_dtype") //사용자 역할
public abstract class User {

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserEnum userRole;

    @Id //식별자
    @Column(name = "user_id", length = 30)
    private String userId;

    @Column(name = "password", length = 30, nullable = false) //사용자 비밀번호
    @NotNull
    private String userPassword;

    @Column(name = "user_name", length = 20, nullable = false) //사용자 이름
    @NotNull
    private String userName;

    @Column(name = "profile_img", length = 100, nullable = false) //사용자 프로필 이미지
    @NotNull
    private String profileImg;

    @Column(unique = true, nullable = false)
    private String email;
}