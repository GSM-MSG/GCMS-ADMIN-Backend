package com.example.msgadminapi.exception.exception;

import com.example.msgadminapi.exception.CommonException;
import com.example.msgadminapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class TeacherNotFoundException extends CommonException {

    public TeacherNotFoundException() {
        super(ErrorCode.TEACHER_NOT_FOUND);
    }
}
