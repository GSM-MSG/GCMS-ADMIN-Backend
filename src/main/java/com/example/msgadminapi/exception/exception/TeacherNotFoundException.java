package com.example.msgadminapi.exception.exception;

import com.example.msgadminapi.exception.CommonException;
import com.example.msgadminapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class TeacherNotFoundException extends CommonException {
    private static final CommonException EXCEPTION = new TeacherNotFoundException();

    public TeacherNotFoundException() {
        super(ErrorCode.TEACHER_NOT_FOUND);
    }
}
