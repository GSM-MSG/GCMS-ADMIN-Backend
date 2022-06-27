package com.example.msgadminapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class StatResponseDto {
    private int total;
    private List<StatisticsResponseDto> afterSchools;
}
