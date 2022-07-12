package com.example.msgadminapi.controller;

import com.example.msgadminapi.configuration.security.jwt.TokenProvider;
import com.example.msgadminapi.configuration.utils.CookieUtil;
import com.example.msgadminapi.dto.request.LoginInDto;
import com.example.msgadminapi.dto.response.AdminCookieResponseDto;
import com.example.msgadminapi.dto.response.LoginResponseDto;
import com.example.msgadminapi.dto.response.RefreshResponseDto;
import com.example.msgadminapi.exception.exception.RefreshTokenExpiredException;
import com.example.msgadminapi.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
@Slf4j
public class TeacherController {

    private final TeacherService teacherService;
    private final CookieUtil cookieUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AdminCookieResponseDto> login(HttpServletResponse response, @RequestBody LoginInDto LoginInDto) throws Exception {
        System.out.println("response = " + passwordEncoder.encode(LoginInDto.getPassword()));
        LoginResponseDto userInform = teacherService.login(LoginInDto.getUserId(), LoginInDto.getPassword());
        response.addCookie(userInform.getAdminAccessToken());
        response.addCookie(userInform.getAdminRefreshToken());
        AdminCookieResponseDto adminCookieResponseDto = new AdminCookieResponseDto(userInform.getAdminAccessToken().getValue(), userInform.getAdminRefreshToken().getValue());
        return new ResponseEntity<>(adminCookieResponseDto, HttpStatus.OK);
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<AdminCookieResponseDto> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        Cookie refresh = cookieUtil.getCookie(request, "adminRefreshToken");
        if(refresh == null) {
            throw new RefreshTokenExpiredException();
        }
        RefreshResponseDto responseDto = teacherService.reIssueAccessToken(refresh.getValue());
        response.addCookie(responseDto.getAdminAccessToken());
        response.addCookie(responseDto.getAdminRefreshToken());
        AdminCookieResponseDto adminCookieResponseDto = new AdminCookieResponseDto(responseDto.getAdminAccessToken().getValue(), responseDto.getAdminRefreshToken().getValue());
        return new ResponseEntity<>(adminCookieResponseDto, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<Boolean> logout(HttpServletRequest request) {
        Cookie[] getcookie = request.getCookies();
        if(getcookie == null) return null;
        for(Cookie cookie : getcookie) {
            if(cookie.getName().equals("accessToken")) {
                teacherService.logout(cookie.getValue());
            }
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
