package com.example.msgadminapi.controller;

import com.example.msgadminapi.dto.response.UserResponseDto;
import com.example.msgadminapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserResponseDto> userFindAll() {
        return userService.findAll();
    }

}