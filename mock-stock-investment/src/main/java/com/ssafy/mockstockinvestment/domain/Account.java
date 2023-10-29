package com.ssafy.mockstockinvestment.domain;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "accounts") //DB 테이블
@Data //getter, setter, 기본 생성자, toString() 메서드
public class Account {
    @Id //식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ID값 자동으로 올라가게 설정
    @Column(name="account_id")
    private Long accountId;

    @Column(name="balance") //잔액
    @NotNull
    private Integer balance;

    @OneToOne
    @JoinColumn(name="user_id_fk") //사용자
    @NotNull
    private Student studentIdFK;
}
