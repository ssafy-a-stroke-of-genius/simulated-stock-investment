package com.ssafy.mockstockinvestment.project.dto;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "invite_codes") //DB 테이블
@Data //getter, setter, 기본 생성자, toString() 메서드
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InviteCode {
    @Id //식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ID값 자동으로 올라가게 설정
    @Column(name="invite_id")
    private Long inviteId;

    @Column(name="invite_code", length = 20) //초대코드
    @NotNull
    private String inviteCode;

    @Column(name="code_created_at") //코드 생성일
    @NotNull
    private Timestamp codeCreatedAt;

    @Column(name="code_expired_at") //코드 만료일
    @NotNull
    private Timestamp codeExpiredAt;

    @ManyToOne
    @JoinColumn(name="project_id_fk") //연결 프로젝트
    @NotNull
    private Project projectIdFK;
}
