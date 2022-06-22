package com.example.msgadminapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class JwtResponseDto {
    private String email;
    private String accessToken;
    private String refreshToken;
}
