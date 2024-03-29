package com.hamitmizrak.ui.rest;

import com.hamitmizrak.business.dto.EmployeeDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IEmployeeRest {

    //SAVE
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    //LIST
    List<EmployeeDto> getAllEmployees();

    //FIND
    ResponseEntity<EmployeeDto> getEmployeeById(Long id);

    //DELETE
    ResponseEntity<Map<String,Boolean>> deleteEmployee(Long id);

    //UPDATE
    ResponseEntity<EmployeeDto> updateEmployee(Long id, EmployeeDto employeeDto);
}
