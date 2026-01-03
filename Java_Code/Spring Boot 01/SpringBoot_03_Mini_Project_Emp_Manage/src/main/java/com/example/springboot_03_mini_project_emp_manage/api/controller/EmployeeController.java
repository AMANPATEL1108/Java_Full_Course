package com.example.springboot_03_mini_project_emp_manage.api.controller;

import com.example.springboot_03_mini_project_emp_manage.api.dto.EmployeeCreateDto;
import com.example.springboot_03_mini_project_emp_manage.api.entity.Employee;
import com.example.springboot_03_mini_project_emp_manage.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getEmployee() {
        HashMap<String, Object> response = new HashMap<>();
        List<Employee> e = employeeService.getEmployee(new EmployeeCreateDto());
        response.put("employees", e);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getByEmployeeId(@PathVariable Long id) {
        HashMap<String, Object> response = new HashMap<>();
        Employee e = employeeService.getEmployeeById(id);
        if (e == null) {
            response.put("message", "Employee not found");
            return ResponseEntity.badRequest().body(response);
        }
        response.put("employee", e);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(employeeService.searchByName(name));
    }

    @GetMapping("/filter/department")
    public ResponseEntity<?> filterByDepartment(@RequestParam String department) {
        return ResponseEntity.ok(employeeService.filterByDepartment(department));
    }

    @GetMapping("/filter/designation")
    public ResponseEntity<?> filterByDesignation(@RequestParam String designation) {
        return ResponseEntity.ok(employeeService.filterByDesignation(designation));
    }

    @GetMapping("/filter/status")
    public ResponseEntity<?> filterByStatus(@RequestParam String status) {
        return ResponseEntity.ok(employeeService.filterByStatus(status));
    }

    @GetMapping("/filter/salary")
    public ResponseEntity<?> filterBySalary(
            @RequestParam Double minSalary,
            @RequestParam Double maxSalary) {
        return ResponseEntity.ok(employeeService.filterBySalaryRange(minSalary, maxSalary));
    }

    @GetMapping("/report/total")
    public ResponseEntity<?> getTotalEmployees() {
        Long total = employeeService.getTotalEmployees();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/report/department-count")
    public ResponseEntity<?> getDepartmentWiseCount() {
        List<Object[]> data = employeeService.getDepartmentWiseCount();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/report/highest-salary")
    public ResponseEntity<?> getHighestSalaryEmployee() {
        return ResponseEntity.ok(employeeService.getHighestSalaryEmployee());
    }

    @GetMapping("/report/average-salary")
    public ResponseEntity<?> getAverageSalary(@RequestParam String department) {
        return ResponseEntity.ok(employeeService.getAverageSalary(department));
    }
}