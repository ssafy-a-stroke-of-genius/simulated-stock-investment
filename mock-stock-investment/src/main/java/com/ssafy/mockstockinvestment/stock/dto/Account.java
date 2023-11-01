package com.ssafy.mockstockinvestment.stock.dto;

import com.ssafy.mockstockinvestment.user.dto.Student;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "accounts") //DB 테이블
@Data //getter, setter, 기본 생성자, toString() 메서드
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
    @Id //식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ID값 자동으로 올라가게 설정
    @Column(name="account_id")
    private Long accountId;

    @Column(name="balance",nullable = false) //잔액
    @NotNull
    private Integer balance;

    @OneToOne
    @JoinColumn(name="user_id_fk", nullable = false) //사용자
    @NotNull
    private Student studentIdFK;
}
