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

// 사용자 정보를 나타내는 엔티티 클래스
@Entity(name = "users") // DB의 'users' 테이블과 매핑
@Data // Lombok 라이브러리를 사용하여 getter, setter, toString() 등 자동 생성
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 단일 테이블 상속 전략 사용
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자를 protected로 생성
@DiscriminatorColumn(name = "user_role_dtype") // 상속 받는 클래스 구분을 위한 컬럼

public abstract class User {

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserEnum userRole; // 사용자 역할

    @Id // 기본키
    @Column(name = "user_id", length = 30)
    private String userId; // 사용자 ID

    @Column(name = "password", length = 30, nullable = false) // 사용자 비밀번호, Null 허용하지 않음
    @NotNull
    private String userPassword;

    @Column(name = "user_name", length = 20, nullable = false) // 사용자 이름, Null 허용하지 않음
    @NotNull
    private String userName;

    @Column(name = "profile_img", length = 100, nullable = false) // 사용자 프로필 이미지 URL, Null 허용하지 않음
    @NotNull
    private String profileImg;

    @Column(unique = true, nullable = false)
    private String email; // 이메일, 고유해야 하며 Null 허용하지 않음
}
