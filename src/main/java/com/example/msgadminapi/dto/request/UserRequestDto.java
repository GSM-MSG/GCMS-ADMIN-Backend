package com.example.msgadminapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequestDto {
    private String email;
    private String name;
    private String grade;
}


