package com.ssafy.mockstockinvestment.domain;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "companies") //DB 테이블
@Data //getter, setter, 기본 생성자, toString() 메서드
public class Company {
    @Id //식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ID값 자동으로 올라가게 설정
    @Column(name="company_id")
    private Integer companyId;

    @Column(name="stock_price", length = 4) //기준 가격
    @NotNull
    private Integer stockPrice;

    @Column(name="company_name", length = 10) //회사명
    @NotNull
    private String companyName;

    @Column(name="company_detail", length = 1000) //회사 설명
    private String companyDetail;
}
