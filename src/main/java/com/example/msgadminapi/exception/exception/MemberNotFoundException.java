package com.example.msgadminapi.exception.exception;

import com.example.msgadminapi.exception.CommonException;
import com.example.msgadminapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MemberNotFoundException extends CommonException {

    public MemberNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND);
    }
}
