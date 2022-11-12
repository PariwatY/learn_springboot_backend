package com.example.allianz.service.employee;

import java.util.List;
import java.util.Optional;

import com.example.allianz.entity.employee.Employee;
import com.example.allianz.exception.BaseException;
import com.example.allianz.model.employee.*;

import com.example.allianz.model.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {


    /**
     * @param email , password, name for create employee
     * @return name and email of employee
     * @summary this function using for register
     */
    Employee create(String email, String password, String name) throws BaseException;


    /**
     * @param request this request using for create employee
     * @return name and email of employee
     * @summary this function using for register
     */
    RegisterResponse register(RegisterRequest request) throws BaseException;

    /**
     * @param request this request using for searching employee, checking password etc.
     * @return email and jwt to client
     * @summary this function using for login
     */
    DataResponse<LoginResponse> login(DataRequest<LoginRequest> request) throws BaseException;


    /**
     * @param token using for verify token
     * @param request using for verify token
     * @return all employee
     * @summary this function using for getting all employee data
     */

    DataResponse<List<EmployeeData>> getAllEmployee(String token,LoginRequest request) throws BaseException;

    /**
     * @param token using for verify token
     * @param email using for verify token
     * @return employee
     * @summary this function using for getting employee data by id
     */
    DataResponse<EmployeeData>  getEmployee(String token,String email,String employeeId) throws BaseException;

    /**
     * @param token using for verify token
     * @param request using for getting email to verify token and getting name to update
     * @param employeeId using for update
     * @return all employee
     * @summary this function using for getting employee data by id
     */
    DataResponse<EmployeeData>  updateEmployee(String token, DataRequest<EmployeeData> request, String employeeId) throws BaseException;


    /**
     * @param token using for verify token
     * @param email using for verify token
     * @param name using for update
     * @param employeeId using for update
     * @return all employee
     * @summary this function using for getting employee data by id
     */
    ResponseEntity<DataResponse<String>> deleteEmployee(String token, String email, String name, String employeeId) throws BaseException;

    /**
     * @return all employee
     * @summary this function using for call database repository for get all employee
     */
    List<Employee> findAll();


    /**
     * @param id using for find employee
     * @return  employee
     * @summary this function using for call database repository for getting employee by id
     */
    Optional<Employee> findById(String id);


    /**
     * @param email using for find employee
     * @return  employee
     * @summary this function using for call database repository for getting employee by email
     */
    Optional<Employee> findByEmail(String email);


    /**
     * @param user using for update employee
     * @return  employee
     * @summary this function using for call database repository for update employee data
     */
    Employee update(Employee user);

    /**
     * @param id find employee for updating
     * @param name using for update employee name
     * @return  employee
     * @summary this function using for call database repository for update employee name
     */
    Employee updateName(String id, String name) throws BaseException;


    /**
     * @param id delete employee by id
     * @return  employee
     * @summary this function using for call database repository for deleting employee by id
     */
    void deleteById(String id);


}
