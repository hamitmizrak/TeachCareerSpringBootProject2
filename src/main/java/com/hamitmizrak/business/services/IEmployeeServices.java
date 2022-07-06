package com.hamitmizrak.business.services;

import com.hamitmizrak.business.dto.EmployeeDto;
import com.hamitmizrak.data.entity.EmployeeEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IEmployeeServices {

    //Model Mapper
    public EmployeeDto  entityToDto(EmployeeEntity employeeEntity);
    public EmployeeEntity  dtoToEntity(EmployeeDto employeeDto);

    //save
    public EmployeeDto createEmployee(EmployeeDto employeeDto);

    //list
    public List<EmployeeDto> getAllEmployees();

    //find
    public ResponseEntity<EmployeeDto> getEmployeeById(Long id);

    //delete
    public ResponseEntity<Map<String,Boolean>> deleteEmployeeById(Long id);

    //update
    public ResponseEntity<EmployeeDto> updateEmployee(Long id,EmployeeDto employeeDto);
}
