package com.ssafy.mockstockinvestment.stock.repository;

import com.ssafy.mockstockinvestment.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query(value = "select s from Stock s where s.stockNowDate between ?1 and ?2 and s.stockCompany=?3")
    List<Stock> getStockList(String startDate, String endDate, String company);
}
