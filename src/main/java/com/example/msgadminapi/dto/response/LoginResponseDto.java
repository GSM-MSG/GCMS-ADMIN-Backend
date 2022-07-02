package com.example.msgadminapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseCookie;

import javax.servlet.http.Cookie;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class LoginResponseDto {
    private Cookie accessToken;
    private Cookie refreshToken;
}
