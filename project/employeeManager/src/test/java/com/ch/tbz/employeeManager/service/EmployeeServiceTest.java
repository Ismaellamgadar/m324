package com.ch.tbz.employeeManager.service;

import com.ch.tbz.employeeManager.entity.Employee;
import com.ch.tbz.employeeManager.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    void testCreateEmployee() {
        Employee employee = new Employee("Jane", "Doe", new Date(), 4);

        employeeService.createEmployee(employee);

        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void testGetAllEmployees() {
        Employee employee1 = new Employee("John", "Doe", new Date(), 3);
        Employee employee2 = new Employee("Jane", "Smith", new Date(), 4);

        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        List<Employee> employees = employeeService.getAllEmployees();

        assertEquals(2, employees.size());
        verify(employeeRepository, times(1)).findAll();
    }
}
