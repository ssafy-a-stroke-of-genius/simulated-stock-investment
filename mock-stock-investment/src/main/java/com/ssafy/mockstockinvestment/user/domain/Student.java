package com.ssafy.mockstockinvestment.user.domain;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.mockstockinvestment.project.Project;
import com.ssafy.mockstockinvestment.stock.Stock;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("학생")
public class Student extends User {

    //Id 자체가 식별자..?

    @ManyToOne
    private Project project;

    @OneToMany(mappedBy = "stockId")
    private List<Stock> stockList = new ArrayList<>();
}