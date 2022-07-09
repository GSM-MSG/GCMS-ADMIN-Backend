package com.example.msgadminapi.exception.exception;

import com.example.msgadminapi.exception.CommonException;
import com.example.msgadminapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class RefreshTokenExpiredException extends CommonException {

    public RefreshTokenExpiredException() {
        super(ErrorCode.REFRESH_TOKEN_EXPIRED);
    }
}
