package com.example.msgadminapi.service;

import com.example.msgadminapi.domain.entity.user.User;
import com.example.msgadminapi.domain.repository.UserRepository;
import com.example.msgadminapi.dto.request.UserRequestDto;
import com.example.msgadminapi.dto.response.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
                .grade(Integer.parseInt(String.valueOf(e.getGrade()) + String.valueOf(e.getClass_()) + String.valueOf(e.getNum())))
                .build()));
        return users;
    }

    @Transactional
    public void userModify(UserRequestDto userRequestDto) {
        int grade = Integer.parseInt(userRequestDto.getGrade().substring(0, 1));
        int class_ = Integer.parseInt(userRequestDto.getGrade().substring(1, 2));
        int num = Integer.parseInt(userRequestDto.getGrade().substring(2, 4));

        User userEntity = userRepository.findByEmail(userRequestDto.getEmail());

        userEntity.update(grade, class_, num);
    }

    @Transactional
    public List<UserResponseDto> search(String keyword) {
        List<UserResponseDto> searchList = new ArrayList<>();
        userRepository.findByNameContaining(keyword)
                .forEach(e -> searchList.add(UserResponseDto.builder()
                        .email(e.getEmail())
                        .name(e.getName())
                        .grade(e.getGrade())
                        .build()));
        return searchList;
    }

    @Transactional
    public void userDelete(String email) {
        userRepository.deleteById(email);
    }


}
