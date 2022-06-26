package com.example.msgadminapi.exception.Handler;

import com.example.msgadminapi.exception.ErrorResponse;
import com.example.msgadminapi.exception.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> UserNotFoundExceptionHandler(HttpServletRequest request, UserNotFoundException ex) {
        printException(request, ex, "User Not Found");
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(ClubNotFoundException.class)
    public ResponseEntity<ErrorResponse> ClubNotFoundExceptionHandler(HttpServletRequest request, ClubNotFoundException ex) {
        printException(request, ex, "Club Not Found");
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> MemberNotFoundExceptionHandler(HttpServletRequest request, MemberNotFoundException ex) {
        printException(request, ex, "Member Not Found");
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(AfterSchoolNotFoundException.class)
    public ResponseEntity<ErrorResponse> AfterSchoolNotFoundExceptionHandler(HttpServletRequest request, AfterSchoolNotFoundException ex) {
        printException(request, ex, "AfterSchool Not Found");
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(TeacherNotFoundException.class)
    public ResponseEntity<ErrorResponse> TeacherNotFoundExceptionHandler(HttpServletRequest request, TeacherNotFoundException ex) {
        printException(request, ex, "Teacher Not Found");
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    public void printException(HttpServletRequest request, Exception ex, String message) {
        log.error(request.getRequestURI());
        log.error(message);
        ex.printStackTrace();
    }
}
