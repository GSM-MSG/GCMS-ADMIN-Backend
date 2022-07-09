package com.example.msgadminapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminCookieResponseDto {
    String adminAccessToken;
    String adminRefreshToken;
}
