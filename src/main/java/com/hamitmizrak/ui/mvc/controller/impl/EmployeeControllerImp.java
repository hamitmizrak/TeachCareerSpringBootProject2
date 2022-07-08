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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@Controller
//API gelen verileri RestTemplate alıp sonrasında Spring MVC kullanmak
public class EmployeeControllerImp implements IEmployeeController {



    //Fake Save
    // http://localhost:8080/employee/speedsave
    @GetMapping("employee/speedsave")
    @ResponseBody
    public String saveFlowerList() {

        String url="http://localhost:8080/api/v1/employees";
        RestTemplate restTemplate=new RestTemplate();
        int i=0;
        //karşı tarafta: ProductDto var
        for ( i = 1; i <=10 ; i++) {
            EmployeeRestDto productDto= EmployeeRestDto.builder().employeeId(0L).employeeName("müşteri adı"+i).employeeSurname("müşteri soyadı "+i).build();
            EmployeeRestDto productDto2 =restTemplate.postForObject(url,productDto,EmployeeRestDto.class);
        }
        return i+" adet Employee kaydedildi";
    }

    //Form GetMapping
    //Save
    //http://localhost:8080/employee/form
    @GetMapping("employee/form")
    @Override
    public String employeeControllerSaveGetForm(Model model) {
        model.addAttribute("employee_save", new EmployeeRestDto());
        return "employee_save";
    }

    //Form GetMapping
    //Save
    //http://localhost:8080/employee/form
    @PostMapping("employee/form")
    @Override
    public String employeeControllerSavePostForm(@Valid @ModelAttribute("employee_save") EmployeeRestDto employeeDto, BindingResult bindingResult) {
        String url = "http://localhost:8080/api/v1/employees";
        RestTemplate restTemplate = new RestTemplate();
        //karşı tarafta: ProductDto var
        if (bindingResult.hasErrors()) {
            log.error("Hata oluştu.");
            return "employee_save";
        }
        EmployeeRestDto productDto2 = restTemplate.postForObject(url, employeeDto, EmployeeRestDto.class);
        return "redirect:/employee/list";
    }

    //Listeleme
    // http://localhost:8080/employee/list
    @GetMapping("employee/list")
    @Override
    public String employeeControllerList(Model model) {
        String URL = "http://localhost:8080/api/v1/employees";
        //serverdaki veriyi almak için kullanılır.
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<EmployeeRestDto>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<EmployeeRestDto>>() {
        });
        List<EmployeeRestDto> productDtoList = responseEntity.getBody();
        for (EmployeeRestDto temp : productDtoList) {
            System.out.println(temp);
        }
        model.addAttribute("employee_list", productDtoList);
        log.info(productDtoList);
        return "employee_list";
    }


    //find
    //http://localhost:8080/find/employee/1
    @GetMapping("find/employee/{id}")
    @Override
    public String employeeControllerFind(@PathVariable(name = "id") Long id, Model model) {
        String URL = "http://localhost:8080/api/v1/employees/" + id;

        //serverdaki veriyi almak için kullanılır.
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<EmployeeRestDto> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, HttpEntity.EMPTY, EmployeeRestDto.class);
        log.info(responseEntity);
        model.addAttribute("employee_find",responseEntity.getBody());
        return "employee_detail_pages";
    }


    //delete
    //http://localhost:8080/delete/employee/1
    @GetMapping("delete/employee/{id}")
    @Override
    public String deleteEmployeeById(@PathVariable(name = "id") Long id, Model model) {
        String URL = "http://localhost:8080/api/v1/employees/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<EmployeeRestDto> responseEntity = restTemplate.exchange(URL, HttpMethod.DELETE, HttpEntity.EMPTY, EmployeeRestDto.class);
        log.info(responseEntity);
        model.addAttribute("employee_delete",responseEntity.getBody());
        return "redirect:/employee/list";
    }

    //Update
    //http://localhost:8080/update/employee/1
    @GetMapping("update/employee/{id}")
    @Override
    public String employeeControllerUpdateGetForm(@PathVariable(name = "id") Long id, Model model) {
        String URL = "http://localhost:8080/api/v1/employees/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<EmployeeRestDto> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, HttpEntity.EMPTY, EmployeeRestDto.class);
        EmployeeRestDto productDto = responseEntity.getBody();
        model.addAttribute("employee_update",productDto);
        System.out.println("Hepsi"+productDto);
        return "employee_update";
    }



    //Form GetMapping
    //Update
    //http://localhost:8080/update/employee/1
    @PostMapping("update/employee/{id}")
    @Override
    public String employeeControllerUpdatePostForm(@Valid @ModelAttribute("employee_update") EmployeeRestDto employeeRestDto,@PathVariable(name = "id") Long id, BindingResult bindingResult) {

        String url = "http://localhost:8080/api/v1/employees";  //+id;
        RestTemplate restTemplate = new RestTemplate();

        if (bindingResult.hasErrors()) {
            log.error("Hata oluştu.");
            return "employee_update";
        }
        EmployeeRestDto productDto2 = restTemplate.postForObject(url, employeeRestDto, EmployeeRestDto.class);
        return "redirect:/employee/list";
    }




}
