package com.example.msgadminapi.exception.exception;

import com.example.msgadminapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MemberNotFindException extends RuntimeException {
    private ErrorCode errorCode;

    public MemberNotFindException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
