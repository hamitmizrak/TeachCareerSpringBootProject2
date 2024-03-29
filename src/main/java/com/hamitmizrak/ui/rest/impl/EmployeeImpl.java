package com.hamitmizrak.ui.rest.impl;

import com.hamitmizrak.business.dto.EmployeeDto;
import com.hamitmizrak.business.services.IEmployeeServices;

import com.hamitmizrak.ui.rest.IEmployeeRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
//Dış dünyaya açılacak kapı ...
public class EmployeeImpl implements IEmployeeRest {

    @Autowired
    IEmployeeServices services;

    //http://localhost:8080/api/v1
    //http://localhost:8080/api/v1/index
    @GetMapping({"/","/index"})
    public String getRoot(){
        return "index";
    }


    //SAVE
    //http://localhost:8080/api/v1/employees
    @Override
    @PostMapping("/employees")
    public EmployeeDto createEmployee( @RequestBody EmployeeDto employeeDto) {
        services.createEmployee(employeeDto);
        return employeeDto;
    }

    //LIST
    //http://localhost:8080/api/v1/employees
    @Override
    @GetMapping("/employees")
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> list=services.getAllEmployees();
        return list;
    }

    //FIND
    //http://localhost:8080/api/v1/employees/1
    @Override
    @GetMapping ({"/employees/{id}","/employees/"})
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name="id",required = false) Long id) {
        ResponseEntity<EmployeeDto> dto= services.getEmployeeById(id);
        return dto;
    }

    //DELETE
    //http://localhost:8080/api/v1/employees/1
    @Override
    @DeleteMapping({"/employees/{id}","/employees/"})
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable(name="id",required = false) Long id) {
        services.deleteEmployee(id);
        Map<String,Boolean> response=new HashMap<>();
        response.put("silindi",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    //UPDATE
    //http://localhost:8080/api/v1/employees/1
    @Override
    @PostMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable(name="id") Long id, @RequestBody EmployeeDto employeeDto) {
       services.updateEmployee(id,employeeDto);
        return ResponseEntity.ok(employeeDto);
    }
}
