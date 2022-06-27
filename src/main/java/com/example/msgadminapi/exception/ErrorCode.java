package com.example.msgadminapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND(404, "No User found", ErrorClassification.USER + "-ERR-404"),
    CLUB_NOT_FOUND(404, "No Club Found", ErrorClassification.CLUB + "-ERR-404"),
    MEMBER_NOT_FOUND(404, "No Club Members Found", ErrorClassification.MEMBER + "-ERR-404"),
    AFTERSCHOOL_NOT_FOUND(404, "No AfterSchool Found", ErrorClassification.AFTERSCHOOL + "-ERR-404"),
    TEACHER_NOT_FOUND(404, "No Teacher Found", ErrorClassification.TEACHER + "-ERR-404"),
    REFRESH_TOKEN_EXPIRED(401, "RefreshToken is Expired", ErrorClassification.COMMON + "-ERR-401")
    ;

    private int status;
    private String msg;
    private String details;
}
