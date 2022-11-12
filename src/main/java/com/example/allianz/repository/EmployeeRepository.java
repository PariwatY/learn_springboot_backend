package com.example.allianz.repository;

import java.util.List;
import java.util.Optional;

import com.example.allianz.entity.employee.Employee;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeeRepository extends CrudRepository<Employee, Id> {


    List<Employee> findAll();

    Optional<Employee> findByEmail(String email);

    Optional<Employee> findById(String id);

    boolean existsById(String email);

    @Transactional
    void deleteById(String id);
}
