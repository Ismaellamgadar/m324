package com.ch.tbz.employeeManager.controller;

import com.ch.tbz.employeeManager.entity.Employee;
import com.ch.tbz.employeeManager.repository.EmployeeRepository;
import com.ch.tbz.employeeManager.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    private final EmployeeRepository employeeRepository;


    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    @PostMapping
    public String createEmployee(@Valid @RequestBody Employee employee) {
       this.employeeService.createEmployee(employee);
        return "Employee created with firstname: " + employee.getFirstname() +
                " and lastname: " + employee.getLastname();
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();

        if (employees.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No employees found.");
        }
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getTicketById(@PathVariable String id) {
        Optional<Employee> ticket = employeeRepository.findById(Long.valueOf(id));
        return ticket.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
