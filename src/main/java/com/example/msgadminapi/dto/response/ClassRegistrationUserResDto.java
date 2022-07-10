package com.example.msgadminapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassRegistrationUserResDto {
    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "grade")
    private int grade;

    @JsonProperty(value = "class")
    private int class_;

    @JsonProperty(value = "num")
    private int num;

    @JsonProperty(value = "userImg")
    private String userImg;
}
