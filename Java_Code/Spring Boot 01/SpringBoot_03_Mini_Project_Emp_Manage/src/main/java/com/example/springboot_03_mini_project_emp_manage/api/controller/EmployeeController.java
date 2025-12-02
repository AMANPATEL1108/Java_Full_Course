package com.example.springboot_03_mini_project_emp_manage.api.controller;


import com.example.springboot_03_mini_project_emp_manage.api.EmployeeService;
import com.example.springboot_03_mini_project_emp_manage.api.dto.EmployeeCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public String createEmployee(EmployeeCreateDto user){
        try{
            employeeService.createEmployee(user);
        }catch (Exception e){
            return e.toString();
        }
    }
}
