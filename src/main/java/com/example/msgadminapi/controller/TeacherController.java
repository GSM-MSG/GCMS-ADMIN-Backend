package com.example.msgadminapi.controller;

import com.example.msgadminapi.dto.request.LoginInDto;
import com.example.msgadminapi.dto.response.LoginResponseDto;
import com.example.msgadminapi.dto.response.RefreshResponseDto;
import com.example.msgadminapi.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginInDto LoginInDto) throws Exception {
        return new ResponseEntity<>(teacherService.login(LoginInDto.getEmail(), LoginInDto.getPassword()), HttpStatus.OK);
    }

    @GetMapping("/refreshtoken")
    public ResponseEntity<RefreshResponseDto> refreshToken(HttpServletRequest request) {
        RefreshResponseDto responseDto = teacherService.reIssueAccessToken(request.getHeader("RefreshToken"));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
