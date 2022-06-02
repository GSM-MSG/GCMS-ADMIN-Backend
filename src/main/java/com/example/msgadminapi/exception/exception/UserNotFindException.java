package com.example.msgadminapi.exception.exception;

import com.example.msgadminapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class UserNotFindException extends RuntimeException {
    private ErrorCode errorCode;

    public UserNotFindException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
