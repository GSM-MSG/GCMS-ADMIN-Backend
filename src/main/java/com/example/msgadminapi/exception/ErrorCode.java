package com.example.msgadminapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_NOT_FIND(404, "User not found", ErrorClassification.USER + "-ERR-404"),
    CLUB_NOT_FIND(404, "No Club Found", ErrorClassification.CLUB + "-ERR-404"),
    MEMBER_NOT_FIND(404, "No Club Members Found", ErrorClassification.MEMBER + "-ERR-404"),
    AFTERSCHOOL_NOT_FIND(404, "No AfterSchool Found", ErrorClassification.AFTERSCHOOL + "-ERR-404"),
    ;

    private int status;
    private String msg;
    private String details;
}
