package com.example.msgadminapi.service;

import com.example.msgadminapi.configuration.security.jwt.TokenProvider;
import com.example.msgadminapi.domain.entity.teacher.Teacher;
import com.example.msgadminapi.domain.repository.TeacherRepository;
import com.example.msgadminapi.dto.response.LoginResponseDto;
import com.example.msgadminapi.dto.response.RefreshResponseDto;
import com.example.msgadminapi.exception.exception.TeacherNotFoundException;
import com.example.msgadminapi.exception.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Value("${teacher.email}")
    private String email;


    public LoginResponseDto login(String email, String password) {
        Teacher teacher = teacherRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException());
        checkPassword(password, teacher.getPassword());

        final String accessToken = tokenProvider.generateAccessToken(teacher.getEmail());
        final String refreshToken = tokenProvider.generateRefreshToken(teacher.getEmail());

        return LoginResponseDto.builder()
                .email(teacher.getEmail())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void checkPassword(String pw, String encodePw) {
        //boolean isSame = passwordEncoder.matches(pw, encodePw);
        boolean isSame = pw.equals(encodePw);
        if(!isSame) {
            throw new UserNotFoundException();
        }
    }

    public RefreshResponseDto reIssueAccessToken(String rfToken) {
        Teacher teacher = teacherRepository.findByEmail(email).orElseThrow(() -> new TeacherNotFoundException());
        tokenProvider.checkRefreshToken(email, rfToken);
        String accessToken = tokenProvider.generateAccessToken(email);
        String refreshToken = tokenProvider.generateRefreshToken(email);
        return new RefreshResponseDto(accessToken, refreshToken);
    }
}
