package com.example.allianz.controller;


import com.example.allianz.exception.BaseException;
import com.example.allianz.model.util.DataResponse;
import com.example.allianz.model.util.HeaderData;
import com.example.allianz.model.util.StatusData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorAdvisor {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<DataResponse<String>> handleBaseException(BaseException e){

        HeaderData headerData = new HeaderData();
        headerData.setResponseDateTime(LocalDateTime.now().toString());

        DataResponse<String> dataResponse = new DataResponse<>();
        dataResponse.setHeader(headerData);
        dataResponse.setStatus(new StatusData().errorStatusData(null,e.getMessage()));

        return new ResponseEntity<>(dataResponse, HttpStatus.EXPECTATION_FAILED);
    }


} 