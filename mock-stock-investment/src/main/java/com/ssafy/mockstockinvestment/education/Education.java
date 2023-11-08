package com.ssafy.mockstockinvestment.education;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name="education_contents")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Education {
    @Id
    @Column(name="category")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Enumerated(EnumType.STRING)
    private EducationCategoryEnum category; //영상 분류
    @Column(name="title",nullable = false)
    @NotNull
    private String title; //제목
    @Column(name="uploaded_at",nullable = false)
    @NotNull
    private Timestamp uploadedAt; //업로드 일자

    @Column(name="source",nullable = false)
    @NotNull
    private String source; //주소
}
