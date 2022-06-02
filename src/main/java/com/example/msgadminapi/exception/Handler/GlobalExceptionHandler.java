package com.example.msgadminapi.exception.Handler;

import com.example.msgadminapi.exception.ErrorResponse;
import com.example.msgadminapi.exception.exception.ClubNotFindException;
import com.example.msgadminapi.exception.exception.MemberNotFindException;
import com.example.msgadminapi.exception.exception.UserNotFindException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFindException.class)
    public ResponseEntity<ErrorResponse> UserNotFindExceptionHandler(HttpServletRequest request, UserNotFindException ex) {
        printException(request, ex, "User Not Find");
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(ClubNotFindException.class)
    public ResponseEntity<ErrorResponse> ClubNotFindExceptionHandler(HttpServletRequest request, ClubNotFindException ex) {
        printException(request, ex, "Club Not Find");
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(MemberNotFindException.class)
    public ResponseEntity<ErrorResponse> MemberNotFindExceptionHandler(HttpServletRequest request, MemberNotFindException ex) {
        printException(request, ex, "Member Not Find");
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    public void printException(HttpServletRequest request, Exception ex, String message) {
        log.error(request.getRequestURI());
        log.error(message);
        ex.printStackTrace();
    }
}
