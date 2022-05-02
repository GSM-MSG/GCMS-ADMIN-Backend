package com.example.msgadminapi.service;

import com.example.msgadminapi.domain.repository.UserRepository;
import com.example.msgadminapi.dto.response.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponseDto> findAll() {
        List<UserResponseDto> users = new ArrayList<>();
        userRepository.findAll().forEach(e -> users.add(UserResponseDto.builder()
                .email(e.getEmail())
                .name(e.getName())
                .grade(String.valueOf(e.getGrade()) + String.valueOf(e.getClass_()) + String.valueOf(e.getNum()))
                .build()));
        return users;
    }
}
