package com.example.msgadminapi.controller;

import com.example.msgadminapi.dto.request.LoginInDto;
import com.example.msgadminapi.dto.response.LoginResponseDto;
import com.example.msgadminapi.dto.response.RefreshResponseDto;
import com.example.msgadminapi.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(HttpServletResponse response, @RequestBody LoginInDto LoginInDto) throws Exception {
        System.out.println("LoginInDto = " + LoginInDto.getUserId());
        System.out.println("LoginInDto = " + LoginInDto.getPassword());
        LoginResponseDto userInform = teacherService.login(LoginInDto.getUserId(), LoginInDto.getPassword());
        response.addCookie(userInform.getAccessToken());
        response.addCookie(userInform.getRefreshToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/refreshtoken")
    public ResponseEntity<RefreshResponseDto> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] getcookie = request.getCookies();
        if(getcookie == null) return null;
        for(Cookie cookie : getcookie) {
            System.out.println("refresh cookies " + cookie.getName());
            System.out.println("refresh cookies value " + cookie.getValue());
            if(cookie.getName().equals("refreshToken")) {
                RefreshResponseDto responseDto = teacherService.reIssueAccessToken(cookie.getValue());
                response.addCookie(responseDto.getAccessToken());
                response.addCookie(responseDto.getRefreshToken());
                return new ResponseEntity<>(responseDto, HttpStatus.OK);
            }
        }
        return null;
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
