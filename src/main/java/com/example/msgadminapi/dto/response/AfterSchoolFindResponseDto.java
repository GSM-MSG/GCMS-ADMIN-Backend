package com.example.msgadminapi.dto.response;

import com.example.msgadminapi.domain.entity.afterschool.enums.Season;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AfterSchoolFindResponseDto {
    private Integer id;
    private String title;
    private List<Integer> grade;
    private List<String> dayOfWeek;
    private String teacher;
    private Season season;
    private Integer yearOf;
}
