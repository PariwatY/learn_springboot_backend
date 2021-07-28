package com.example.leraning.api;

import java.time.LocalDateTime;

import com.example.leraning.exception.BaseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import lombok.Data;

@ControllerAdvice
public class ErrorAdvisor {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException e){
        ErrorResponse response = new ErrorResponse();
        response.setError(e.getMessage());
        response.setStatus(HttpStatus.EXPECTATION_FAILED.value());

        return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        
    }

    @Data
    public static class ErrorResponse{
        private LocalDateTime timestamp = LocalDateTime.now();
        private int status;
        private String error;
    }
} 