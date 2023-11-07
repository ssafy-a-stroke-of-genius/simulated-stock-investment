package com.ssafy.mockstockinvestment.project;

import com.ssafy.mockstockinvestment.user.Manager;
import com.ssafy.mockstockinvestment.user.Student;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "projects") //DB 테이블
@Data //getter, setter, 기본 생성자, toString() 메서드
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {
    @Id //식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ID값 자동으로 올라가게 설정
    @Column(name="project_id")
    private Long projectId;

    @Column(name="project_name", length = 30) //프로젝트명
    @NotNull
    private String projectName;

    @Column(name="project_start_time") //개장시간
    private Timestamp projectStartTime;

    @Column(name="project_end_time") //마감시간
    private Timestamp projectEndTime;

    @Column(name="project_start_date") //프로젝트 시작일
    private Timestamp projectStartDate;

    @Column(name="project_end_date") //프로젝트 종료일
    private Timestamp projectEndDate;

    @Column(name="project_max_percent") //최대 등락률
    private Double projectMaxPercent;

    @Column(name="project_min_percent") //최소 등락률
    private Double projectMinPercent;

    @Column(name="project_era") //현재 프로젝트 시대
    @NotNull
    private Integer projectEra;

    private Timestamp projectNowTime; //현재 시간

    @Column(name="project_now_status", length = 9) //프로젝트 상태
    @NotNull
    private String projectNowStatus;

    @OneToOne
    private InviteCode projectInviteCode; //초대 코드

    @ManyToOne
    private Manager projectOwner; //프로젝트 만든 사람

    @OneToMany(mappedBy = "userId")
    private List<Student> studentList=new ArrayList<>();
    /*
    @OneToMany //일대다
    @JoinColumn(name="user_id_fk") //구성원
    @NotNull
    private User userRole;
    */
}
