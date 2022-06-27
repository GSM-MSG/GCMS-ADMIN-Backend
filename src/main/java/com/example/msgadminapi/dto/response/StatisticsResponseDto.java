package com.example.msgadminapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor @NoArgsConstructor
public class StatisticsResponseDto {
    @JsonProperty
    private Integer afterSchoolIdx;
    @JsonProperty
    private String afterSchoolTitle;
    @JsonProperty
    private int total;
}
