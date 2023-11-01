package com.ssafy.mockstockinvestment.project.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "eras") //DB 테이블
@Data //getter, setter, 기본 생성자, toString() 메서드
public class Era {
    @Id //식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ID값 자동으로 올라가게 설정
    @Column(name="era_id")
    private Integer eraId;

    @Column(name="era", length = 10) //시대
    @NotNull
    private Integer era;

    @Column(name="era_detail", length = 1000) //시대 설명
    private String eraDetail;

    @Column(name="era_interest_rate", length = 10) //시대별 금리
    @NotNull
    private Double eraInterestRate;

}
