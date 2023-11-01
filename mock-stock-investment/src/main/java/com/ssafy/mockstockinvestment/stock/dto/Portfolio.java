package com.ssafy.mockstockinvestment.stock.dto;

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
