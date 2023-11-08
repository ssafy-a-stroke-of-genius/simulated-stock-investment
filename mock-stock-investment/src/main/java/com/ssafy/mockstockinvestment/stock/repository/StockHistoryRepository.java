package com.ssafy.mockstockinvestment.stock.repository;

import com.ssafy.mockstockinvestment.stock.StockHistory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StockHistoryRepository extends JpaRepository<StockHistory,Long> {

}
