package com.ch.tbz.employeeManager.service;


import com.ch.tbz.employeeManager.entity.Employee;
import com.ch.tbz.employeeManager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public void createEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    public Optional<Employee> findById(Long id) {
        return this.employeeRepository.findById(id);
    }
}
