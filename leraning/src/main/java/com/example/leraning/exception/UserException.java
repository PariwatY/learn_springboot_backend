package com.example.leraning.exception;

public class UserException extends BaseException {

    public UserException(String code) {
        super("user." + code);
    }

    public static UserException requestNull() {
        return new UserException("register.request.null");
    }

    public static UserException emailNull() {
        return new UserException("register.email.null");
    }

    // Create
    public static UserException createEmailNull() {
        return new UserException("register.email.null");
    }

    public static UserException createEmailDuplicate() {
        return new UserException("register.email.duplicate");
    }

    public static UserException createNameNull() {
        return new UserException("register.name.null");
    }

    public static UserException creatPasswordNull() {
        return new UserException("register.password.null");

    }

    // Login
    public static UserException loginFailEmailNotFound() {
        return new UserException("login.fail");
    }

    public static UserException loginFailPasswordIncorrect() {
        return new UserException("login.fail");
    }

    // Update
    public static UserException notFound() {
        return new UserException("not.found");
    }

    public static UserException unAuthorized() {
        return new UserException("unauthorized");
    }
}