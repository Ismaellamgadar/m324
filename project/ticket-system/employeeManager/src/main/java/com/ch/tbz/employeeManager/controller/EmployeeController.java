package com.ch.tbz.employeeManager.controller;

import com.ch.tbz.employeeManager.entity.Employee;
import com.ch.tbz.employeeManager.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public String createEmployee(@Valid @RequestBody Employee employee) {
       this.employeeService.createEmployee(employee);
        return "Employee created with firstname: " + employee.getFirstname() +
                " and lastname: " + employee.getLastname();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable String id) {
        Optional<Employee> employee = this.employeeService.findById(Long.valueOf(id));
        return employee.orElse(null);
    }
}
