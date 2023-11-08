package com.ssafy.mockstockinvestment.stock.service;

import com.ssafy.mockstockinvestment.account.Account;
import com.ssafy.mockstockinvestment.account.AccountRepository;
import com.ssafy.mockstockinvestment.stock.Stock;
import com.ssafy.mockstockinvestment.stock.StockHistory;
import com.ssafy.mockstockinvestment.stock.dto.StockDealRequest;
import com.ssafy.mockstockinvestment.stock.repository.StockHistoryRepository;
import com.ssafy.mockstockinvestment.stock.repository.StockRepository;
import com.ssafy.mockstockinvestment.user.Student;
import com.ssafy.mockstockinvestment.user.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StockServiceImpl implements StockService{
    private final StockRepository stockRepository;
    private final StockHistoryRepository stockHistoryRepository;
    private final StudentRepository studentRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository, StudentRepository studentRepository, AccountRepository accountRepository,StockHistoryRepository stockHistoryRepository) {
        this.stockRepository = stockRepository;
        this.studentRepository = studentRepository;
        this.accountRepository = accountRepository;
        this.stockHistoryRepository=stockHistoryRepository;
    }

    public static Timestamp StringToTimestamp(String date) throws ParseException {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate=dateFormat.parse(date);
        return new Timestamp(parsedDate.getTime());

    }

    @Override
    public List<Stock> getStockList(String startDate, String endDate, String company) throws ParseException {
        return stockRepository.getStockList(StringToTimestamp(startDate),StringToTimestamp(endDate),company);
    }
    @Transactional
    @Override
    public void sellStock(StockDealRequest stockDealRequest) {
        //stock,student 조회
        Optional<Stock> stockToSell=stockRepository.findById(stockDealRequest.getStockId());
        Optional<Student> studentToSell=studentRepository.findById(stockDealRequest.getUserId());

        //해당하는 주식이 존재한다면 진행
        if(stockToSell.isPresent()&&studentToSell.isPresent()){
            //주식 구매 내역 로그 생성
            StockHistory stockHistory=stockDealRequest.createStockHistory(stockToSell.get(),studentToSell.get());
            stockHistoryRepository.save(stockHistory);

            //오늘 집계 계좌에 구매 정보 업데이트
            Account account=accountRepository.findAccount(
                    studentToSell.get(),
                    stockDealRequest.getDealDate(),
                    stockToSell.get().getCompanyIdFK().getCompanyName()
            );
            account.toBuilder()
                    .quantity(account.getQuantity()+ stockDealRequest.getQuantity())
                    .build();
            accountRepository.save(account);
        }
    }
    @Transactional
    @Override
    public void buyStock(StockDealRequest stockDealRequest) {

    }
    public void createStock(Stock stock){
        stockRepository.save(stock);
    }
}
