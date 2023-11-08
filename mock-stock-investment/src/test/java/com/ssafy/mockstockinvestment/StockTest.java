package com.ssafy.mockstockinvestment;

import com.ssafy.mockstockinvestment.project.Project;
import com.ssafy.mockstockinvestment.project.ProjectRepository;
import com.ssafy.mockstockinvestment.stock.Company;
import com.ssafy.mockstockinvestment.stock.Stock;
import com.ssafy.mockstockinvestment.stock.repository.CompanyRepository;
import com.ssafy.mockstockinvestment.stock.repository.StockRepository;
import com.ssafy.mockstockinvestment.stock.service.StockService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@SpringBootTest
public class StockTest {
    @Autowired
    StockService stockService;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Test
    @DisplayName("createStock 동작확인")
    public void createStockTest() throws Exception{
        //given
        //// 한국 시간대를 설정합니다.
        ZoneId koreaZoneId = ZoneId.of("Asia/Seoul");
        //// 한국 시간대 기준의 현재 ZonedDateTime을 가져옵니다.
        ZonedDateTime zonedDateTime = ZonedDateTime.now(koreaZoneId);
        //// ZonedDateTime을 Instant로 변환합니다.
        Instant instant = zonedDateTime.toInstant();
        //// Instant를 이용하여 Timestamp 객체를 생성합니다.
        Timestamp timestamp = Timestamp.from(instant);
        Company company=companyRepository.findAll().get(0);
        Project project=projectRepository.findAll().get(0);
        Stock stock=Stock.builder()
                .stockPrice(20000)
                .stockPercent(5.3)
                .companyIdFK(company)
                .stockNowDate(timestamp)
                .projectIdFK(project)
                .build();
        stockService.createStock(stock);
        //when
        
        //then
    }
}
