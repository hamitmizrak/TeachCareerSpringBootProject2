package com.hamitmizrak.business.services.impl;

import com.hamitmizrak.business.dto.EmployeeDto;
import com.hamitmizrak.business.services.IEmployeeServices;
import com.hamitmizrak.data.entity.EmployeeEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class EmployeeServicesImpl implements IEmployeeServices {
    
    @Override
    public EmployeeDto entityToDto(EmployeeEntity employeeEntity) {
        return null;
    }

    @Override
    public EmployeeEntity dtoToEntity(EmployeeDto employeeDto) {
        return null;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        return null;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return null;
    }

    @Override
    public ResponseEntity<EmployeeDto> getEmployeeById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<EmployeeDto> deleteEmployeeById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<EmployeeDto> updateEmployeeById(Long id, EmployeeDto employeeDto) {
        return null;
    }
}