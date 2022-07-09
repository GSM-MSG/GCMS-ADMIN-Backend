package com.example.msgadminapi.exception.exception;

import com.example.msgadminapi.exception.CommonException;
import com.example.msgadminapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class AfterSchoolReduplicationException extends CommonException {

    public AfterSchoolReduplicationException() {
        super(ErrorCode.AFTERSCHOOL_REDUPLICATION);
    }
}
