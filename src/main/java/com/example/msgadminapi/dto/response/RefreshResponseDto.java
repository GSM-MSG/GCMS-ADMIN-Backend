package com.example.msgadminapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseCookie;

import javax.servlet.http.Cookie;

@Getter
@AllArgsConstructor
public class RefreshResponseDto {
    private Cookie adminAccessToken;

    private Cookie adminRefreshToken;
}
