package com.ssafy.mockstockinvestment.user;

import com.ssafy.mockstockinvestment.project.Project;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("관리자")
public class Manager extends User {
    @OneToMany(mappedBy = "projectId")
    private List<Project> projectList=new ArrayList<>();
}
