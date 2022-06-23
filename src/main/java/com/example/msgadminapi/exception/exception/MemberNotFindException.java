package com.example.msgadminapi.exception.exception;

import com.example.msgadminapi.exception.CommonException;
import com.example.msgadminapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MemberNotFindException extends CommonException {
    private CommonException EXCEPTION = new MemberNotFindException();

    public MemberNotFindException() {
        super(ErrorCode.MEMBER_NOT_FIND);
    }
}
