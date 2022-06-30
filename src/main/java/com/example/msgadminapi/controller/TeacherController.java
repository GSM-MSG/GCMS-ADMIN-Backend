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

import javax.servlet.http.HttpServletResponse;

@Controller
@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(HttpServletResponse response, @RequestBody LoginInDto LoginInDto) throws Exception {
        LoginResponseDto userInform = teacherService.login(LoginInDto.getUserId(), LoginInDto.getPassword());
        response.addCookie(userInform.getAccessToken());
        response.addCookie(userInform.getRefreshToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/refreshtoken")
    public ResponseEntity<RefreshResponseDto> refreshToken(@RequestHeader("RefreshToken") String rfToken) {
        RefreshResponseDto responseDto = teacherService.reIssueAccessToken(rfToken);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<Boolean> logout(@RequestHeader("Authorization") String acesstoken) {
        teacherService.logout(acesstoken);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
