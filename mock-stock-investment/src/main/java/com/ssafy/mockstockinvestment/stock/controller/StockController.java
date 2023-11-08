package com.ssafy.mockstockinvestment.stock.controller;

import com.ssafy.mockstockinvestment.stock.Stock;
import com.ssafy.mockstockinvestment.stock.dto.StockDealRequest;
import com.ssafy.mockstockinvestment.stock.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Stock>> getStockList(String startDate, String endDate, String company) throws ParseException{
        return ResponseEntity.ok().body(stockService.getStockList(startDate,endDate,company));
    }
    @PostMapping("/deal")
    public ResponseEntity<?> dealStock(StockDealRequest stockDealRequest){
        stockService.dealStock(stockDealRequest);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/test/stock")
    public ResponseEntity<?> createStock(Stock stock){
        stockService.createStock(stock);
        return ResponseEntity.ok().build();
    }

}
