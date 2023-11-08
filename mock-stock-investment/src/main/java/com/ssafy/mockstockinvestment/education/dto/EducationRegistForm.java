package com.ssafy.mockstockinvestment.education.dto;

import com.ssafy.mockstockinvestment.education.Education;
import com.ssafy.mockstockinvestment.education.EducationCategoryEnum;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Timestamp;

public class EducationRegistForm {
    private EducationCategoryEnum category; //영상 분류
    private String title; //제목
    private Timestamp uploadedAt; //업로드 일자
    private String source; //주소

    /**
     * Education 객체로 변환
     * @return
     */
    public Education toEducation(){
        return Education.builder()
                .source(source)
                .title(title)
                .category(category)
                .uploadedAt(uploadedAt)
                .build();
    }
}
