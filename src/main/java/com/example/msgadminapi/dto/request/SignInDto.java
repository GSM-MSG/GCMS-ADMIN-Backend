package com.example.msgadminapi.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class SignInDto {
    @NotNull(message = "email is not null")
    private String email;
}
