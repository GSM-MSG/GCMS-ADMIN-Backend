package com.example.msgadminapi.dto.request;

import com.example.msgadminapi.domain.entity.afterschool.AfterSchool;
import com.example.msgadminapi.domain.entity.afterschool.DayOfWeek;
import com.example.msgadminapi.domain.entity.afterschool.Grade;
import com.example.msgadminapi.domain.entity.afterschool.enums.Season;
import com.example.msgadminapi.domain.entity.classregistration.ClassRegistration;
import com.example.msgadminapi.domain.entity.member.Member;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class AfterSchoolDto {

    private String title;

    private List<Long> grade;

    private List<String> dayOfWeek;

    private String teacher;

    private Boolean canDuplicate;

    private Season season;

    private Long yearOf;

    public AfterSchool toEntity(List<Grade> grade, List<DayOfWeek> week){
        return AfterSchool.builder()
                .title(title)
                .grade(grade)
                .dayOfWeek(week)
                .teacher(teacher)
                .canDuplicate(canDuplicate)
                .season(season)
                .yearOf(yearOf)
                .build();
    }
}
