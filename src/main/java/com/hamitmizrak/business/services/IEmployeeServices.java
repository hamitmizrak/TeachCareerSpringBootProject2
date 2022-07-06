package com.hamitmizrak.business.services;

import com.hamitmizrak.business.dto.EmployeeDto;
import com.hamitmizrak.data.entity.EmployeeEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

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
    public ResponseEntity<EmployeeDto> deleteEmployeeById(Long id);

    //update
    public ResponseEntity<EmployeeDto> updateEmployeeById(Long id,EmployeeDto employeeDto);
}
