package com.ssafy.mockstockinvestment;

import com.ssafy.mockstockinvestment.stock.Stock;
import com.ssafy.mockstockinvestment.stock.repository.StockRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class StockTest {
    @Autowired
    StockRepository stockRepository;

    @Test
    @DisplayName("getStockList 동작확인")
    public void getStockListTest() throws Exception{
        //given
        List<Stock> stockList=stockRepository.getStockList("2023-11-06","2023-11-06","A회사");
        //when
        
        //then
    }
}
