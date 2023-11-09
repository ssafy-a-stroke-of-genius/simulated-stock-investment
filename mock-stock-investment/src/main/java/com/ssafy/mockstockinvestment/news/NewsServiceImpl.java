package com.ssafy.mockstockinvestment.news;

import com.ssafy.mockstockinvestment.news.dto.DailyNewsList;
import com.ssafy.mockstockinvestment.news.dto.NewsDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService{
    private final NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public static String[] getDate(){
        //날짜 포맷 지정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //오늘 날짜 얻어오기
        // 현재 시간대를 KST로 설정
        ZoneId koreaZoneId = ZoneId.of("Asia/Seoul");
        LocalDate todayLocalDate = LocalDate.now(koreaZoneId);
        // LocalDate를 String으로 변환
        String todayDate=todayLocalDate.format(formatter);
        String yesterdayDate=todayLocalDate.minusDays(1).format(formatter);
        return new String[]{todayDate,yesterdayDate};
    }

    @Override
    public DailyNewsList getDailyNewsList() {
        //오늘과 어제 날짜 구하기
        String[] dates=getDate();
        //구한 날짜를 기반으로 news 제공(추후 프로젝트와 함께 가져오는 방식으로 변경하기, 테스트용)
        List<News> rawToday=newsRepository.findTop4NewsByNewsCreatedDate(dates[0]);
        List<News> rawYesterday=newsRepository.findTop4NewsByNewsCreatedDate(dates[1]);
        //dailynewslist dto 생성
        DailyNewsList dailyNewsList= DailyNewsList.builder().build();
        //news 엔티티를 dto로 교체
        List<NewsDto> today=new ArrayList<>();
        List<NewsDto> yesterday=new ArrayList<>();

        for(News news:rawToday){
            today.add(news.toNewsDto());
        }
        for(News news:rawYesterday){
            yesterday.add(news.toNewsDto());
        }

        //dailynewslist dto에 넣기
        dailyNewsList.toBuilder()
                .today(today)
                .yesterday(yesterday)
                .build();

        return dailyNewsList;
    }
}
