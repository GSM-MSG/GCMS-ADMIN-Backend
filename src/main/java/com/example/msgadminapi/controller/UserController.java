package com.example.msgadminapi.controller;

import com.example.msgadminapi.dto.request.SignInDto;
import com.example.msgadminapi.dto.request.UserRequestDto;
import com.example.msgadminapi.dto.response.JwtResponseDto;
import com.example.msgadminapi.dto.response.UserResponseDto;
import com.example.msgadminapi.response.ResponseService;
import com.example.msgadminapi.response.result.CommonResultResponse;
import com.example.msgadminapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@RequestMapping("users")

public class UserController {

    private final UserService userService;
    private final ResponseService responseService;

    @GetMapping
    public List<UserResponseDto> userFindAll() {
        return userService.findAll();
    }

    @GetMapping("/search")
    public List<UserResponseDto> search(@RequestParam("keyword") String keyword) {
        return userService.search(keyword);
    }

    @PutMapping("/modify")
    public CommonResultResponse userModify(@RequestBody UserRequestDto userRequestDto) {
        userService.userModify(userRequestDto);
        return responseService.getSuccessResult();
    }

    @DeleteMapping
    public CommonResultResponse userDelete(@RequestParam("email") String email) {
        userService.userDelete(email);
        return responseService.getSuccessResult();
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody SignInDto signInDto) throws Exception {
        return new ResponseEntity<>(userService.login(signInDto.getEmail(), signInDto.getPassword()), HttpStatus.OK);
    }

}

