package com.example.msgadminapi.service;

import com.example.msgadminapi.configuration.security.jwt.TokenProvider;
import com.example.msgadminapi.domain.entity.user.User;
import com.example.msgadminapi.domain.repository.UserRepository;
import com.example.msgadminapi.dto.request.UserRequestDto;
import com.example.msgadminapi.dto.response.JwtResponseDto;
import com.example.msgadminapi.dto.response.UserResponseDto;
import com.example.msgadminapi.exception.exception.UserNotFindException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Transactional(readOnly = true)
    public List<UserResponseDto> findAll() {
        List<UserResponseDto> users = new ArrayList<>();
        userRepository.findAll().forEach(e -> users.add(UserResponseDto.builder()
                .email(e.getEmail())
                .name(e.getName())
                .grade(Integer.parseInt("" + e.getGrade() + e.getClass_() + e.getNum()))
                .build()));
        return users;
    }

    @Transactional
    public void userModify(UserRequestDto userRequestDto) {
        int grade = Integer.parseInt(userRequestDto.getGrade().substring(0, 1));
        int class_ = Integer.parseInt(userRequestDto.getGrade().substring(1, 2));
        int num = Integer.parseInt(userRequestDto.getGrade().substring(2, 4));

        User userEntity = userRepository.findByEmail(userRequestDto.getEmail())
                .orElseThrow(() -> new UserNotFindException());
        userEntity.update(grade, class_, num);
    }

    public List<UserResponseDto> search(String keyword) {
            List<User> allByNameLike = userRepository.findAllByNameLike("%" + keyword + "%");
            List<UserResponseDto> response = getUserResponseDtoList(allByNameLike);
            return response;
    }

    @Transactional
    public void userDelete(String email) {
        User byEmail = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFindException());
        userRepository.delete(byEmail);
    }

    private List<UserResponseDto> getUserResponseDtoList(List<User> all) {
        List<UserResponseDto> searchList = new ArrayList<>();
        all.forEach(e -> searchList.add(UserResponseDto.builder()
                .email(e.getEmail())
                .name(e.getName())
                .grade(e.getGrade())
                .build()));
        return searchList;
    }

    public JwtResponseDto login(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFindException());

        final String accessToken = tokenProvider.generateAccessToken(user.getEmail());
        final String refreshToken = tokenProvider.generateRefreshToken(user.getEmail());

        user.updateRefreshToken(refreshToken);

        return JwtResponseDto.builder()
                .email(user.getEmail())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
