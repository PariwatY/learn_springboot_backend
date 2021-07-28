package com.example.leraning.model;

import java.util.Objects;

import lombok.Data;

@Data
public class MRegisterRequest {
    private String email;
    private String password;
    private String name;

}