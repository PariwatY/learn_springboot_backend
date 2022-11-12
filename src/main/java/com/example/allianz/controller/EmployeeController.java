package com.example.allianz.controller;

import com.example.allianz.exception.BaseException;
import com.example.allianz.model.employee.*;
import com.example.allianz.model.util.*;
import com.example.allianz.service.employee.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/employee/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping
    @RequestMapping(value = "/login", produces = "application/json")
    public ResponseEntity<DataResponse<LoginResponse>> login(@RequestBody DataRequest<LoginRequest> request) throws BaseException {
        DataResponse<LoginResponse> response = employeeService.login(request);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping(value = "/register", produces = "application/json")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) throws BaseException {
        RegisterResponse response = employeeService.register(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping
    @RequestMapping(value = "/allEmployee", produces = "application/json")
    public ResponseEntity<DataResponse<List<EmployeeData>>> retrieveAllEmployee(@RequestBody DataRequest<LoginRequest> request, @RequestHeader String token) throws BaseException {
        return new ResponseEntity<>(employeeService.getAllEmployee(token,request.getBody().getData()),HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping(value = "/employee/{id}", produces = "application/json")
    public ResponseEntity<DataResponse<EmployeeData>> retrieveEmployee(@RequestBody DataRequest<LoginRequest> request
            ,@PathVariable String id
            , @RequestHeader String token) throws BaseException {

        return new ResponseEntity<>(employeeService.getEmployee(token,request.getBody().getData().getEmail(),id),HttpStatus.OK);
    }


    @PostMapping
    @RequestMapping( value = "/upEmployee/{id}", produces = "application/json")
    public ResponseEntity<DataResponse<EmployeeData> > updateEmployee(@RequestBody DataRequest<EmployeeData> request
            ,@PathVariable String id
            , @RequestHeader String token) throws BaseException {
        return new ResponseEntity<>(employeeService.updateEmployee(token
                ,request
                , id),HttpStatus.OK);
    }


    @PostMapping
    @RequestMapping( value = "/delEmployee/{id}", produces = "application/json")
    public ResponseEntity<DataResponse<String>> deleteEmployee(@RequestBody DataRequest<EmployeeData> request
            ,@PathVariable String id
            , @RequestHeader String token) throws BaseException {
        return employeeService.deleteEmployee(token
                ,request.getBody().getData().getEmail()
                ,request.getBody().getData().getName()
                , id);
    }




}