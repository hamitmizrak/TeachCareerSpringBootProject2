package com.hamitmizrak.ui.mvc.controller;

import com.hamitmizrak.business.dto.EmployeeDto;
import com.hamitmizrak.business.dto.EmployeeRestDto;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

public interface IEmployeeController {

      public String employeeControllerSaveGetForm(Model model );
      public String employeeControllerSavePostForm(EmployeeRestDto employeeRestDto, BindingResult bindingResult);
      public String employeeControllerList(Model model);
      public String employeeControllerFind( Long id, Model model);
      public String deleteEmployeeById(Long id, Model model);
      public String employeeControllerUpdateGetForm( Long id, Model model);
      public String employeeControllerUpdatePostForm( EmployeeRestDto employeeRestDto, Long id, BindingResult bindingResult);

      }
