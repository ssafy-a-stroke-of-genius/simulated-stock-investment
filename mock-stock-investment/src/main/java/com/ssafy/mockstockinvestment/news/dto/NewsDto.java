package com.ssafy.mockstockinvestment.news.dto;

import com.ssafy.mockstockinvestment.news.News;
import com.ssafy.mockstockinvestment.project.Project;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class NewsDto {
    private Long newsId;
    private String newsTitle;
    private String newsContent;
    private Timestamp newsCreatedDate;
    private Double newsStockRate;
    private String newsStockCompany;
    private Project projectIdFK;

    public News toEntity(){
        return News.builder()
                .newsId(newsId)
                .newsTitle(newsTitle)
                .newsContent(newsContent)
                .newsCreatedDate(newsCreatedDate)
                .newsStockRate(newsStockRate)
                .newsStockCompany(newsStockCompany)
                .projectIdFK(projectIdFK)
                .build();
    }

}
