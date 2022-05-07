package com.example.msgadminapi.controller;

import com.example.msgadminapi.dto.request.UserRequestDto;
import com.example.msgadminapi.dto.response.UserResponseDto;
import com.example.msgadminapi.response.ResponseService;
import com.example.msgadminapi.response.result.CommonResultResponse;
import com.example.msgadminapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final ResponseService responseService;

    @GetMapping("/users")
    public List<UserResponseDto> userFindAll() {
        return userService.findAll();
    }

    @PutMapping("/modify")
    public CommonResultResponse userModify(@RequestBody UserRequestDto userRequestDto) {
        userService.userModify(userRequestDto);
        return responseService.getSuccessResult();
    }

    @GetMapping("/search")
    public List<UserResponseDto> search(@RequestParam("keyword") String keyword) {
        return userService.search(keyword);
    }

    @DeleteMapping("")
    public CommonResultResponse userDelete(@RequestParam("email") String email) {
        userService.userDelete(email);
        return responseService.getSuccessResult();
    }

}

