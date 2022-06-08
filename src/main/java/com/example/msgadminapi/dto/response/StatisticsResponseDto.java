package com.example.msgadminapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor @NoArgsConstructor
public class StatisticsResponseDto {
    private Long afterSchoolIdx;
    private int total;
}
