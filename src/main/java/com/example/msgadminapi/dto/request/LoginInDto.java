package com.example.msgadminapi.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class LoginInDto {
    @NotNull(message = "email is not null")
    private String email;

    @NotNull(message = "password is not null")
    private String password;
}
