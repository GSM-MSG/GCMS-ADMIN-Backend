package com.example.msgadminapi.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class LoginInDto {
    @NotNull(message = "userId is not null")
    private String userId;

    @NotNull(message = "password is not null")
    private String password;
}
