package com.ssafy.mockstockinvestment.account.dto;

import com.ssafy.mockstockinvestment.account.Account;
import com.ssafy.mockstockinvestment.stock.Stock;
import lombok.Data;

import javax.persistence.Transient;

@Data //getter, setter, 기본 생성자, toString() 메서드
public class Portfolio {
    private Long portfolioId;
    @Transient
    private Account account;

    @Transient
    private Stock stock;

}
