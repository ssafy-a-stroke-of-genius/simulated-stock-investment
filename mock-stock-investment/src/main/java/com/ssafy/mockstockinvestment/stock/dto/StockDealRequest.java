package com.ssafy.mockstockinvestment.stock.dto;

import com.ssafy.mockstockinvestment.account.AccountEnum;
import com.ssafy.mockstockinvestment.stock.Stock;
import com.ssafy.mockstockinvestment.stock.StockDealActionEnum;
import com.ssafy.mockstockinvestment.stock.StockHistory;
import com.ssafy.mockstockinvestment.user.Student;
import lombok.*;


/**
 * 주식 거래(구매/판매) 요청을 위한 DTO
 */
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockDealRequest {
    private String userId;
    private Long stockId;
    private Integer quantity;
    private StockDealActionEnum action;
    private String dealDate;
    private AccountEnum assetType;

    /**
     * 거래한 주식과 거래한 학생을 받아 log를 남기기 위한 메서드
     * @param stock
     * @param student
     * @return
     */
    public StockHistory createStockHistory(Stock stock, Student student){
        return StockHistory.builder()
                .act(action)
                .era(student.getProject().getProjectEra())
                .quantity(quantity)
                .stockIdFK(stock)
                .userIdFK(student)
                .build();
    }

}
