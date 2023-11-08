package com.ssafy.mockstockinvestment.stock;

import com.ssafy.mockstockinvestment.project.Project;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "stocks") //DB 테이블
@Data //getter, setter, 기본 생성자, toString() 메서드
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock {
    @Id //식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ID값 자동으로 올라가게 설정
    @Column(name="stock_id")
    private Long stockId;

    private String stockDetail;

    @Column(name="stock_price") //금액
    @NotNull
    private Integer stockPrice;

    @Column(name="stock_percent") //주식 등락률
    @NotNull
    private Double stockPercent;

    @Column(name="stock_now_date") //공시일자
    @NotNull
    private Timestamp stockNowDate;

    @ManyToOne
    @JoinColumn(name="project_id_fk") //프로젝트
    @NotNull
    private Project projectIdFK;

    @OneToOne
    @JoinColumn(name="company_id_fk") //회사
    @NotNull
    private Company companyIdFK;
}
