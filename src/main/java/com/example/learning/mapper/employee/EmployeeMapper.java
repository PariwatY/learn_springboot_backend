package com.example.learning.mapper.employee;

import com.example.learning.entity.employee.Employee;
import com.example.learning.model.employee.EmployeeData;
import com.example.learning.model.employee.RegisterResponse;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    RegisterResponse toRegisterResponse(Employee employee);

    List<RegisterResponse> toListRegisterResponse(List<Employee> employee);

    EmployeeData toEmployeeData(Employee employee);

    List<EmployeeData> toListEmployeeData(List<Employee> employee);

}
