package com.example.springboot_03_mini_project_emp_manage.api.serviceImpl;

import com.example.springboot_03_mini_project_emp_manage.api.dto.EmployeeCreateDto;
import com.example.springboot_03_mini_project_emp_manage.api.entity.Employee;
import com.example.springboot_03_mini_project_emp_manage.api.repository.EmployeeRepository;
import com.example.springboot_03_mini_project_emp_manage.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return loadUserByEmail(email);
    }

    @Override
    public UserDetails loadUserByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    public void createEmployee(EmployeeCreateDto user) {
        if (user.getFirstName() == null || user.getFirstName().isEmpty())
            throw new IllegalArgumentException("First name is required.");
        if (user.getLastName() == null || user.getLastName().isEmpty())
            throw new IllegalArgumentException("Last name is required.");
        if (user.getEmail() == null || user.getEmail().isEmpty())
            throw new IllegalArgumentException("Email is required.");
        if (user.getPhoneNumber() == null || user.getPhoneNumber().isEmpty())
            throw new IllegalArgumentException("Phone number is required.");
        if (user.getAddress() == null || user.getAddress().isEmpty())
            throw new IllegalArgumentException("Address is required.");
        if (user.getDesignation() == null || user.getDesignation().isEmpty())
            throw new IllegalArgumentException("Designation is required.");
        if (user.getSalary() == null) throw new IllegalArgumentException("Salary is required.");
        if (user.getDepartment() == null || user.getDepartment().isEmpty())
            throw new IllegalArgumentException("Department is required.");
        if (user.getDateOfJoining() == null) throw new IllegalArgumentException("Date of joining is required.");
        if (user.getStatus() == null || user.getStatus().isEmpty())
            throw new IllegalArgumentException("Status is required.");
        if (user.getRole() == null || user.getRole().isEmpty()) {
            throw new IllegalArgumentException("Role is Required");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is Required");
        }

        Employee employee = new Employee();
        employee.setFirstName(user.getFirstName());
        employee.setLastName(user.getLastName());
        employee.setEmail(user.getEmail());

        if (!user.getRole().startsWith("ROLE_")) {
            employee.setRole("ROLE_" + user.getRole());
        } else {
            employee.setRole(user.getRole());
        }

        employee.setPassword(passwordEncoder.encode(user.getPassword()));
        employee.setPhoneNumber(user.getPhoneNumber());
        employee.setAddress(user.getAddress());
        employee.setDesignation(user.getDesignation());
        employee.setSalary(user.getSalary());
        employee.setDepartment(user.getDepartment());
        employee.setDateOfJoining(user.getDateOfJoining());
        employee.setStatus(user.getStatus());

        employeeRepository.save(employee);
    }

    public List<Employee> getEmployee(EmployeeCreateDto user) {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee updatedByEmployee(Long id, Employee employee) {
        Employee existing = employeeRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setFirstName(employee.getFirstName());
        existing.setLastName(employee.getLastName());
        existing.setEmail(employee.getEmail());
        existing.setPhoneNumber(employee.getPhoneNumber());
        existing.setAddress(employee.getAddress());
        existing.setDesignation(employee.getDesignation());

        if (!employee.getRole().startsWith("ROLE_")) {
            existing.setRole("ROLE_" + employee.getRole());
        } else {
            existing.setRole(employee.getRole());
        }

        if (employee.getPassword() != null && !employee.getPassword().isEmpty()) {
            existing.setPassword(passwordEncoder.encode(employee.getPassword()));
        }

        existing.setSalary(employee.getSalary());
        existing.setDepartment(employee.getDepartment());
        existing.setDateOfJoining(employee.getDateOfJoining());
        existing.setStatus(employee.getStatus());

        return employeeRepository.save(existing);
    }

    public Employee employeeDeleteByID(Long id) {
        Employee existing = employeeRepository.findById(id).orElse(null);
        if (existing != null) employeeRepository.delete(existing);
        return existing;
    }

    public List<Employee> searchByName(String name) {
        return employeeRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
    }

    public List<Employee> filterByDepartment(String department) {
        return employeeRepository.findByDepartmentIgnoreCase(department);
    }

    public List<Employee> filterByDesignation(String designation) {
        return employeeRepository.findByDesignationIgnoreCase(designation);
    }

    public List<Employee> filterByStatus(String status) {
        return employeeRepository.findByStatusIgnoreCase(status);
    }

    public List<Employee> filterBySalaryRange(Double minSalary, Double maxSalary) {
        return employeeRepository.findBySalaryBetween(minSalary, maxSalary);
    }

    public Long getTotalEmployees() {
        return employeeRepository.getTotalEmployees();
    }

    public List<Object[]> getDepartmentWiseCount() {
        return employeeRepository.getEmployeeCountByDepartment();
    }

    public Employee getHighestSalaryEmployee() {
        return employeeRepository.findTopByOrderBySalaryDesc();
    }

    public Double getAverageSalary(String department) {
        return employeeRepository.getAverageSalaryByDepartment(department);
    }
}