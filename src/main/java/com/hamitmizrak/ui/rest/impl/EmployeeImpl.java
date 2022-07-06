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

    @Override
    //http://localhost:8080/api/v1/employees
    @PostMapping("/employees")
    public EmployeeDto createEmployee( @RequestBody EmployeeDto employeeDto) {
        services.createEmployee(employeeDto);
        return employeeDto;
    }

    @Override
    //http://localhost:8080/api/v1/employees
    @GetMapping("/employees")
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> list=services.getAllEmployees();
        return list;
    }

    @Override
    //http://localhost:8080/api/v1/employees/1
    @PostMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name="id") Long id) {
        ResponseEntity<EmployeeDto> dto= services.getEmployeeById(id);
        return dto;
    }

    @Override
    //http://localhost:8080/api/v1/employees/1
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable(name="id") Long id) {
        services.deleteEmployeeById(id);
        Map<String,Boolean> response=new HashMap<>();
        response.put("silindi",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @Override
    //http://localhost:8080/api/v1/employees/1
    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable(name="id") Long id, @RequestBody EmployeeDto employeeDto) {
       services.updateEmployee(id,employeeDto);
        return ResponseEntity.ok(employeeDto);
    }
}
