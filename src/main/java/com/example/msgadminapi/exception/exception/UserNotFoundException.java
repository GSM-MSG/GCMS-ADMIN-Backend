package com.example.msgadminapi.exception.exception;

import com.example.msgadminapi.exception.CommonException;
import com.example.msgadminapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class UserNotFoundException extends CommonException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
