package com.example.msgadminapi.exception.exception;

import com.example.msgadminapi.exception.CommonException;
import com.example.msgadminapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class AfterSchoolNotFindException extends CommonException {
    private static CommonException EXCEPTION = new AfterSchoolNotFindException();

    public AfterSchoolNotFindException() {
        super(ErrorCode.AFTERSCHOOL_NOT_FIND);
    }
}
