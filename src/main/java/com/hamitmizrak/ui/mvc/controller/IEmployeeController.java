package com.hamitmizrak.ui.mvc.controller;

import com.hamitmizrak.business.dto.EmployeeRestDto;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface IEmployeeController {

    //SpeedDataInsert
    public String speedDataInsert();

    //SAVE
    public String employeeControllerSaveGetForm(Model model);
    public String employeeControllerSavePostForm(EmployeeRestDto employeeRestDto, BindingResult bindingResult);

    //LIST
    public String employeeControllerList(Model model);

    //FIND
    public String employeeControllerFind( Long id,  Model model);

    //DELETE
    public String employeeControllerDelete( Long id,  Model model);

    //UPDATE
    public String employeeControllerUpdateGetForm(Long id,Model model);
    public String employeeControllerUpdatePostForm(EmployeeRestDto employeeRestDto,Long id, BindingResult bindingResult);

}
