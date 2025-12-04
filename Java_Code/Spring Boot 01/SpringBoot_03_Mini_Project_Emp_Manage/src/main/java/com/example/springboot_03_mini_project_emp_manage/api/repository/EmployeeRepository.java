package com.example.springboot_03_mini_project_emp_manage.api.repository;

import com.example.springboot_03_mini_project_emp_manage.api.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);

    List<Employee> findByDepartmentIgnoreCase(String department);

    List<Employee> findByDesignationIgnoreCase(String designation);

    List<Employee> findByStatusIgnoreCase(String status);

    List<Employee> findBySalaryBetween(Double minSalary, Double maxSalary);
    @Query("SELECT COUNT(e) FROM Employee e")
    Long getTotalEmployees();

    @Query("SELECT e.department, COUNT(e) FROM Employee e GROUP BY e.department")
    List<Object[]> getEmployeeCountByDepartment();

}
