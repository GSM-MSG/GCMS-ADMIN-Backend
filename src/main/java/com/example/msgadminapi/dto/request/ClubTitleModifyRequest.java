package com.example.msgadminapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClubTitleModifyRequest {
    private Long id;
    private String title;
}
