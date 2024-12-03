package com.ch.tbz.employeeManager.repository;

import com.ch.tbz.employeeManager.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testSaveAndFindById() {
        Employee employee = new Employee("John", "Doe", new Date(), 3);
        Employee savedEmployee = employeeRepository.save(employee);

        Optional<Employee> foundEmployee = employeeRepository.findById(savedEmployee.getId());

        assertTrue(foundEmployee.isPresent());
        assertEquals("John", foundEmployee.get().getFirstname());
        assertEquals("Doe", foundEmployee.get().getLastname());
    }

    @Test
    void testDeleteEmployee() {
        Employee employee = new Employee("John", "Doe", new Date(), 3);
        Employee savedEmployee = employeeRepository.save(employee);

        employeeRepository.deleteById(savedEmployee.getId());

        Optional<Employee> foundEmployee = employeeRepository.findById(savedEmployee.getId());
        assertFalse(foundEmployee.isPresent());
    }
}
