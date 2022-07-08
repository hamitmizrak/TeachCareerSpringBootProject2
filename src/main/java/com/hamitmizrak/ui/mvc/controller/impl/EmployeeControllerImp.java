package com.hamitmizrak.ui.mvc.controller.impl;

import com.hamitmizrak.business.dto.EmployeeRestDto;
import com.hamitmizrak.ui.mvc.controller.IEmployeeController;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@Log4j2
public class EmployeeControllerImp implements IEmployeeController {

    //speed Insert
    // http://localhost:8080/employee/speedSave
    @Override
    @ResponseBody
    @GetMapping("employee/speedSave")
    public String speedDataInsert() {
        String URL = "http://localhost:8080/api/v1/employees";//dikkat sonuna root yazma
        RestTemplate restTemplate = new RestTemplate();
        int i = 0;
        for (i = 1; i <= 10; i++) {
            EmployeeRestDto employeeRestDto = EmployeeRestDto.builder().employeeId(0L).employeeName("Müşteri adı: " + i).employeeSurname("Müşteri soyadı: " + i).build();
            restTemplate.postForObject(URL, employeeRestDto, EmployeeRestDto.class);
        }
        return i + " tane veri eklendi";
    }

    //SAVE GET
    @GetMapping("employee/form")
    @Override
    public String employeeControllerSaveGetForm(Model model) {
        model.addAttribute("employee_save", new EmployeeRestDto());
        return "employee_list";
    }

    //SAVE POST
    @Override
    public String employeeControllerSavePostForm(EmployeeRestDto employeeRestDto, BindingResult bindingResult) {
        return null;
    }

    //LIST
    // http://localhost:8080/employee/list
    @GetMapping("employee/list")
    @Override
    public String employeeControllerList(Model model) {
         RestTemplate restTemplate = new RestTemplate();
         String URL = "http://localhost:8080/api/v1/employees";//dikkat sonuna root yazma
        ResponseEntity<List<EmployeeRestDto>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<EmployeeRestDto>>() {
        });
        List<EmployeeRestDto> dtoList = responseEntity.getBody();
        model.addAttribute("employee_list", dtoList);
        return "employee_list";
    }

    //FIND
    @Override
    public String employeeControllerFind(Long id, Model model) {
        return null;
    }

    //DELETE
    // http://localhost:8080/delete/employee/4
    @GetMapping("delete/employee/{id}")
    @Override
    public String employeeControllerDelete(@PathVariable(name="id") Long id, Model model) {
        //"http://localhost:8080/api/v1/employees"  /1
        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://localhost:8080/api/v1/employees/"+id;//dikkat sonuna root yazma
        ResponseEntity<EmployeeRestDto> responseEntity = restTemplate.exchange(URL, HttpMethod.DELETE, HttpEntity.EMPTY,EmployeeRestDto.class);
        model.addAttribute("employee_delete",responseEntity.getBody());
        return "redirect:/employee/list";
    }


    //UPDATE GET
    @Override
    public String employeeControllerUpdateGetForm(Long id, Model model) {
        return null;
    }


    //UPDATE POST
    @Override
    public String employeeControllerUpdatePostForm(EmployeeRestDto employeeRestDto, Long id, BindingResult bindingResult) {
        return null;
    }
}
