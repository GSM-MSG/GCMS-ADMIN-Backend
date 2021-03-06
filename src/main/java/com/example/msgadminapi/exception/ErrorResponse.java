package com.example.msgadminapi.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private String message;
    private String details;

    public ErrorResponse(ErrorCode errorCode) {
        this.message = errorCode.getMsg();
        this.details = errorCode.getDetails();
    }

}
