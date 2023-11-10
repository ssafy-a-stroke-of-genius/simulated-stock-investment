package com.ssafy.mockstockinvestment.user.domain;

import com.ssafy.mockstockinvestment.project.Project;
import com.ssafy.mockstockinvestment.stock.Stock;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DiscriminatorValue("학생")
public class Student extends User {
    @ManyToOne
    private Project project;

    @OneToMany(mappedBy = "stockId")
    private List<Stock> stockList=new ArrayList<>();
}
