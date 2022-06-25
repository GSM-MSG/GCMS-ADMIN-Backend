package com.example.msgadminapi.controller

import com.example.msgadminapi.dto.request.UserRequestDto
import com.example.msgadminapi.dto.response.UserResponseDto
import com.example.msgadminapi.response.ResponseService
import com.example.msgadminapi.response.result.CommonResultResponse
import com.example.msgadminapi.service.UserService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
class UserController(
    private val userService: UserService,
    private val responseService: ResponseService
) {
    @GetMapping
    fun userFindAll(): List<UserResponseDto> {
        return userService.findAll()
    }

    @GetMapping("/search")
    fun search(@RequestParam("keyword") keyword: String?): List<UserResponseDto> {
        return userService.search(keyword)
    }

    @PutMapping("/modify")
    fun userModify(@RequestBody userRequestDto: UserRequestDto?): CommonResultResponse {
        userService.userModify(userRequestDto)
        return responseService.successResult
    }

    @DeleteMapping
    fun userDelete(@RequestParam("email") email: String?): CommonResultResponse {
        userService.userDelete(email)
        return responseService.successResult
    }
}