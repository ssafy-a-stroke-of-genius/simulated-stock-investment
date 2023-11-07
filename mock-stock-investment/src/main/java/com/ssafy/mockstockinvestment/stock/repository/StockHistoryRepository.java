package com.ssafy.mockstockinvestment.stock.repository;

import com.ssafy.mockstockinvestment.stock.Stock;
import com.ssafy.mockstockinvestment.stock.StockHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockHistoryRepository extends JpaRepository<StockHistory,Long> {

}
