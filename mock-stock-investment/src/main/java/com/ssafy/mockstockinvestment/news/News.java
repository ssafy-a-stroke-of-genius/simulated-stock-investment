package com.ssafy.mockstockinvestment.news;

import com.ssafy.mockstockinvestment.project.Project;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "news") //DB 테이블
@Data //getter, setter, 기본 생성자, toString() 메서드
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class News {
    @Id //식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ID값 자동으로 올라가게 설정
    @Column(name="news_id")
    private Long newsId;

    @Column(name="news_title", length = 100) //제목
    @NotNull
    private String newsTitle;

    @Column(name="news_content", length = 1000) //내용
    @NotNull
//    @Lob 제안!!!
    private String newsContent;

    @Column(name="created_at") //생성일자
    @NotNull
    private Timestamp newsCreatedDate;

    @Column(name="news_stock_rate") //주식 등락률
    @NotNull
    private Double newsStockRate;

    @Column(name="news_stock_company", length = 10) //관련 회사
    @NotNull
    private String newsStockCompany;

    @ManyToOne
    @JoinColumn(name="project_id_fk") //프로젝트
    @NotNull
    private Project projectIdFK;
}
