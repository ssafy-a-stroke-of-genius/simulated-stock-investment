package com.ssafy.mockstockinvestment.stock.repository;

import com.ssafy.mockstockinvestment.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;


public interface StockRepository extends JpaRepository<Stock, Long> {
    /**
     * 한 회사의 기간별 주가 변동 내역 조회
     * @param startDate
     * @param endDate
     * @param company
     * @return
     */
    //string을 timestamp로 변경할것
    @Query(value = "select s from stocks s where s.stockNowDate between :startDate and :endDate and s.companyIdFK.companyName = :company")
    List<Stock> getStockList(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate, @Param("company") String company);

}
