package com.example.msgadminapi.exception.exception;

import com.example.msgadminapi.exception.CommonException;
import com.example.msgadminapi.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ClubNotFoundException extends CommonException {

    public ClubNotFoundException() {
        super(ErrorCode.CLUB_NOT_FOUND);
    }
}
