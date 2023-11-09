package com.ssafy.mockstockinvestment.news;

import com.ssafy.mockstockinvestment.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News,Long> {
    List<News> findTop4NewsByNewsCreatedDate(String date);
    List<News> findTop4NewsByNewsCreatedDateAndProjectIdFK(String date, Project project);


}
