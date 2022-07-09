package com.example.msgadminapi.service;

import com.example.msgadminapi.configuration.security.jwt.TokenProvider;
import com.example.msgadminapi.configuration.utils.CookieUtil;
import com.example.msgadminapi.domain.entity.teacher.Teacher;
import com.example.msgadminapi.domain.repository.TeacherRepository;
import com.example.msgadminapi.dto.response.LoginResponseDto;
import com.example.msgadminapi.dto.response.RefreshResponseDto;
import com.example.msgadminapi.exception.exception.TeacherNotFoundException;
import com.example.msgadminapi.exception.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final CookieUtil cookieUtil;

    @Value("${teacher.user-id}")
    private String userId;


    public LoginResponseDto login(String userId, String password) {
        log.info("user Id 2{}", userId);
        log.info("user Password 2{}", password);
        Teacher teacher = teacherRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException());
        checkPassword(password, teacher.getPassword());
        final Cookie accessToken = cookieUtil.createCookie("adminAccessToken", tokenProvider.generateAccessToken(teacher.getUserId()), TokenProvider.ACCESS_TOKEN_EXPIRE_TIME);
        final Cookie refreshToken = cookieUtil.createCookie("adminRefreshToken", tokenProvider.generateRefreshToken(teacher.getUserId()), TokenProvider.REFRESH_TOKEN_EXPIRE_TIME);


        return LoginResponseDto.builder()
                .adminAccessToken(accessToken)
                .adminRefreshToken(refreshToken)
                .build();
    }

    private void checkPassword(String pw, String encodePw) {
        System.out.println("checkPassword : " +  pw);
        System.out.println("encodePw = " + encodePw);
        //boolean isSame = passwordEncoder.matches(pw, encodePw);
        boolean isSame = pw.equals(encodePw);
        log.info("isSame {}", isSame);
        if(!isSame) {
            throw new UserNotFoundException();
        }
    }

    public RefreshResponseDto reIssueAccessToken(String rfToken) {
        Teacher teacher = teacherRepository.findByUserId(userId).orElseThrow(() -> new TeacherNotFoundException());
        tokenProvider.checkRefreshToken(userId, rfToken);
        final Cookie accessToken = cookieUtil.createCookie("adminAccessToken", tokenProvider.generateAccessToken(teacher.getUserId()), TokenProvider.ACCESS_TOKEN_EXPIRE_TIME);
        final Cookie refreshToken = cookieUtil.createCookie("adminRefreshToken", tokenProvider.generateRefreshToken(teacher.getUserId()), TokenProvider.REFRESH_TOKEN_EXPIRE_TIME);
        return new RefreshResponseDto(accessToken, refreshToken);
    }

    public void logout(String accessToken) {
        tokenProvider.logout(userId, accessToken);
    }
}
