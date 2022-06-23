package com.example.msgadminapi.exception.exception;

import com.example.msgadminapi.exception.CommonException;
import com.example.msgadminapi.exception.ErrorCode;

public class UserNotFindException extends CommonException {
    private static final CommonException EXCEPTION = new UserNotFindException();

    public UserNotFindException() {
        super(ErrorCode.USER_NOT_FIND);
    }
}
