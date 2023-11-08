package com.ssafy.mockstockinvestment.stock.controller;

import com.ssafy.mockstockinvestment.stock.Stock;
import com.ssafy.mockstockinvestment.stock.dto.StockDealRequest;
import com.ssafy.mockstockinvestment.stock.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * 추후 사용자 조회를 통해 프로젝트 데이터 가져오도록 수정하기
     * 231108
     */
    @GetMapping("/")
    public ResponseEntity<List<Stock>> getStockList(@RequestParam("start_date") String startDate, @RequestParam("end_date") String endDate, @RequestParam("company") String company) throws ParseException{
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
