package com.ssafy.mockstockinvestment.account;

import com.ssafy.mockstockinvestment.user.domain.Student;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "accounts") //DB 테이블
@Getter //getter, setter, 기본 생성자, toString() 메서드
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
    @Id //식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ID값 자동으로 올라가게 설정
    @Column(name="account_id")
    private Long accountId;

    @Column(name="balance") //금액
    private Integer balance;

    @Column(name="total_quantity")//보유 수량
    private Integer quantity;

    @Column(name="asset_type", nullable = false)//항목(종류)
    @Enumerated(EnumType.STRING)
    @NotNull
    private AccountEnum assetType;

    @Column(name="created_at") //일자
    @NotNull
    private Timestamp createdAt;

    @Column(name="era") //시대정보
    @NotNull
    private Integer era;

    @OneToOne
    @JoinColumn(name="user_id_fk", nullable = false) //사용자
    @NotNull
    private Student studentIdFK;
}
