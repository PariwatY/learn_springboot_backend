package com.example.allianz.entity.employee;


import javax.persistence.*;

import com.example.allianz.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "employee")
@Data
public class Employee extends BaseEntity {
    @Column(nullable = false, unique = true, length = 60)
    private String email;

    @Column(nullable = false, length = 120)
    private String password;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = true, length = 10)
    private String role;


}
