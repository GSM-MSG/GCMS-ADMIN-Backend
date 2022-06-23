package com.example.msgadminapi.exception.exception;

import com.example.msgadminapi.exception.CommonException;
import com.example.msgadminapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ClubNotFindException extends CommonException {
    private static CommonException EXCEPTION = new ClubNotFindException();

    public ClubNotFindException() {
        super(ErrorCode.CLUB_NOT_FIND);
    }
}
