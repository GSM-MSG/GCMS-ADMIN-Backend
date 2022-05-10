package com.example.msgadminapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // 모든 변수를 파라미터로 받는생성자를 생성
public class ClubTitleModifyRequest {
    private Long id;
    private String title;
}
