package com.example.msgadminapi.dto.request;

import com.example.msgadminapi.domain.entity.afterschool.enums.Season;
import lombok.Getter;

import java.util.List;

@Getter
public class AfterSchoolModifyDto {

    private String title;

    private List<Integer> grade;

    private List<String> dayOfWeek;

    private String teacher;

    private Boolean canDuplicate;

    private Season season;

    private Integer yearOf;
}
