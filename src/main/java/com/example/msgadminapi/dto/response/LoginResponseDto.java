package com.example.msgadminapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class LoginResponseDto {
    private String accessToken;
    private String refreshToken;
}
