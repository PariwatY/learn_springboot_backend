package com.example.learning.model.employee;

import lombok.Data;

@Data
public class LoginResponse {
    private String id;
    private String email;
    private String jwt;
}
