package com.example.msgadminapi.dto.request;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor
public class SignInDto {
    @NotNull(message = "author is not null")
    private String email;
}
