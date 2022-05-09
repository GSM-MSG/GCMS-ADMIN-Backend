package com.example.msgadminapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClubResponseDto {
    private Long id;
    private String type;
    private String bannerUrl;
    private String title;
    private String description;
    private String contact;
    private String teacher;
    private boolean isOpened;
}
