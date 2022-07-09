package com.example.msgadminapi.exception.exception;

import com.example.msgadminapi.exception.CommonException;
import com.example.msgadminapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class AfterSchoolNotFoundException extends CommonException {

    public AfterSchoolNotFoundException() {
        super(ErrorCode.AFTERSCHOOL_NOT_FOUND);
    }
}
