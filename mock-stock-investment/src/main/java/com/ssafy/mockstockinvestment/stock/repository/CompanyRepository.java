package com.ssafy.mockstockinvestment.stock.repository;

import com.ssafy.mockstockinvestment.stock.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
