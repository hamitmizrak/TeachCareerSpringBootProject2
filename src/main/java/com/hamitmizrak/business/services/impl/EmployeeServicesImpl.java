package com.hamitmizrak.business.services.impl;

import com.hamitmizrak.business.dto.EmployeeDto;
import com.hamitmizrak.business.services.IEmployeeServices;
import com.hamitmizrak.data.entity.EmployeeEntity;
import com.hamitmizrak.data.entity.repository.IEmployeeRepository;
import com.hamitmizrak.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class EmployeeServicesImpl implements IEmployeeServices {

    @Autowired
    IEmployeeRepository repository;

    @Autowired
    ModelMapper modelMapper;

    // Model Mapper
    // entityToDto
    @Override
    public EmployeeDto entityToDto(EmployeeEntity employeeEntity) {
        EmployeeDto dto=  modelMapper.map(employeeEntity,EmployeeDto.class);
        return dto;
    }

    // Model Mapper
    // dtoToEntity
    @Override
    public EmployeeEntity dtoToEntity(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity=    modelMapper.map(employeeDto,EmployeeEntity.class);
        return employeeEntity;
    }

    // CRUD
    // SAVE
    // http://localhost:8080/save/employees
    @Override
    @PostMapping("/save/employees")
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        EmployeeEntity entity=  dtoToEntity(employeeDto);
        repository.save(entity);
        return employeeDto;
    }


    // LIST
    // http://localhost:8080/list/employees
    @Override
    @GetMapping("/list/employees")
    public List<EmployeeDto> getAllEmployees() {
       List<EmployeeEntity> listem= repository.findAll();
       List<EmployeeDto> dtoList = new ArrayList<>();
       for(EmployeeEntity entity   :listem){
           EmployeeDto dto=  entityToDto(entity);
           dtoList.add(dto);
       }
        return dtoList;
    }

    // FINDBYID
    // http://localhost:8080/find/employees/1
    @Override
    @GetMapping({"/find/employees","/find/employees/{id}"})
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name="id",required = false) Long id) {
        EmployeeEntity entity= repository.findById(id).orElseThrow( ()->new ResourceNotFoundException(id+"id yoktur"));
        EmployeeDto dto=entityToDto(entity);
        return ResponseEntity.ok(dto);
    }

    // DELETE
    // http://localhost:8080/delete/employees/1
    @Override
    @DeleteMapping({"/delete/employees","/delete/employees/{id}"})
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable(name="id",required = false) Long id) {
        EmployeeEntity entity= repository.findById(id).orElseThrow( ()->new ResourceNotFoundException(id+"id yoktur"));
        repository.delete(entity);
        Map<String,Boolean> response=new HashMap<String,Boolean>();
        response.put("Silindi",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // PUT(GÜNCELLEMEK)
    // http://localhost:8080/update/employees/1
    @Override
    @PostMapping({"/update/employees","/update/employees/{id}"})
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable(name="id",required = false) Long id, EmployeeDto employeeDto) {
        EmployeeEntity entityFind= repository.findById(id).orElseThrow( ()->new ResourceNotFoundException(id+"id yoktur"));
        EmployeeEntity entity = dtoToEntity(employeeDto);

        entityFind.setEmployeeName(entity.getEmployeeName());
        entityFind.setEmployeeSurname(entity.getEmployeeSurname());

        EmployeeEntity saveEntity=  repository.save(entityFind);

        EmployeeDto dto= entityToDto(saveEntity);

        return ResponseEntity.ok(dto);
    }
}
