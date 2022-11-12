package com.example.allianz.model.employee;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String name;

}