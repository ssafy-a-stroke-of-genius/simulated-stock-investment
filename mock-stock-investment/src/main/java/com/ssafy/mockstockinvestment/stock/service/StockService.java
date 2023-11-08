package com.ssafy.mockstockinvestment.stock.service;

import com.ssafy.mockstockinvestment.stock.Stock;
import com.ssafy.mockstockinvestment.stock.dto.StockDealRequest;
import com.ssafy.mockstockinvestment.user.Student;

import java.text.ParseException;
import java.util.List;

public interface StockService {
    /**
     * 한 회사의 기간별 주가 변동 내역 조회
     * @param startDate
     * @param endDate
     * @param company
     * @return
     */
    List<Stock> getStockList(String startDate, String endDate, String company) throws ParseException;

    /**
     * 사용자 아이디, 판매 주식, 판매 수량을 가진 DTO를 통한 판매 요청
     * @param stockDealRequest
     */
    void sellStock(StockDealRequest stockDealRequest);
    /**
     * 사용자 아이디, 구매 주식, 구매 수량을 가진 DTO를 통한 구매 요청
     * @param stockDealRequest
     */
    void buyStock(StockDealRequest stockDealRequest);
    void createStock(Stock stock);
}
