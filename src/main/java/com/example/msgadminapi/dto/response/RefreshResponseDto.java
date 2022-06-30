package com.example.msgadminapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.Cookie;

@Getter
@AllArgsConstructor
public class RefreshResponseDto {
    private Cookie accessToken;

    private Cookie refreshToken;
}
