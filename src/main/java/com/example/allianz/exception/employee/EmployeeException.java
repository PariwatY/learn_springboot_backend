package com.example.allianz.exception.employee;

import com.example.allianz.exception.BaseException;

public class EmployeeException extends BaseException {

    public EmployeeException(String code) {
        super("employee " + code);
    }

    public static EmployeeException requestNull() {
        return new EmployeeException("register request null");
    }

    public static EmployeeException emailNull() {
        return new EmployeeException("register email null");
    }

    // Create
    public static EmployeeException createEmailNull() {
        return new EmployeeException("register email null");
    }

    public static EmployeeException createEmailDuplicate() {
        return new EmployeeException("register email duplicate");
    }

    public static EmployeeException createNameNull() {
        return new EmployeeException("register name null");
    }

    public static EmployeeException creatPasswordNull() {
        return new EmployeeException("register password null");

    }

    // Login
    public static EmployeeException loginFailEmailNotFound() {
        return new EmployeeException("login fail");
    }

    public static EmployeeException loginFailPasswordIncorrect() {
        return new EmployeeException("login fail");
    }

    // Update
    public static EmployeeException notFound() {
        return new EmployeeException("not found");
    }




    // Delete
    public static EmployeeException deleteFail() {
        return new EmployeeException("delete fail");
    }
}