package com.ssafy.mockstockinvestment.news.dto;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Getter
public class DailyNewsList {
    private String todayDate;
    private String yesterdayDate;
    List<NewsDto> today;
    List<NewsDto> yesterday;
    @Builder(toBuilder = true)
    public DailyNewsList(List<NewsDto> today, List<NewsDto> yesterday){
        this.today=today;
        this.yesterday=yesterday;
        //날짜 포맷 지정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //오늘 날짜 얻어오기
        // 현재 시간대를 KST로 설정
        ZoneId koreaZoneId = ZoneId.of("Asia/Seoul");
        LocalDate todayLocalDate = LocalDate.now(koreaZoneId);
        // LocalDate를 String으로 변환
        String todayDate=todayLocalDate.format(formatter);
        String yesterdayDate=todayLocalDate.minusDays(1).format(formatter);
        this.todayDate=todayDate;
        this.yesterdayDate=yesterdayDate;
    }
}
