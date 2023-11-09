package com.ssafy.mockstockinvestment.news;

import com.ssafy.mockstockinvestment.news.dto.DailyNewsList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/news")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    //조회, 추후 사용자 확인해서 돌려줄 짜
    @GetMapping("/")
    public ResponseEntity<DailyNewsList> getDailyNewsList(){
        return ResponseEntity.ok().body(newsService.getDailyNewsList());
    }
}
