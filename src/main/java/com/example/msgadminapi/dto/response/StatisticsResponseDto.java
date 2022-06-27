package com.example.msgadminapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor @NoArgsConstructor
public class StatisticsResponseDto {
    @JsonProperty
    private Long afterSchoolIdx;
    @JsonProperty
    private String afterSchoolTitle;
    @JsonProperty
    private List<String> dayOfWeekList;
    @JsonProperty
    private List<Long> grade;
    @JsonProperty
    private int attend;
}
