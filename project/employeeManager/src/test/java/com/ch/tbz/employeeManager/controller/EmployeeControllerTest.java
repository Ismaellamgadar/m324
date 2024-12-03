package com.ch.tbz.employeeManager.controller;

import com.ch.tbz.employeeManager.entity.Employee;
import com.ch.tbz.employeeManager.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateEmployee() throws Exception {
        Employee employee = new Employee("John", "Doe", new Date(), 3);

        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee created with firstname: John and lastname: Doe"));

        verify(employeeService, times(1)).createEmployee(Mockito.any(Employee.class));
    }

    @Test
    void testCreateEmployeeValidationError() throws Exception {
        Employee invalidEmployee = new Employee("", "Doe", null, 6);

        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidEmployee)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.firstname").value("Firstname is mandatory"))
                .andExpect(jsonPath("$.startDate").value("Start date is mandatory"))
                .andExpect(jsonPath("$.skillLevel").value("must be less than or equal to 5"));

        verify(employeeService, times(0)).createEmployee(Mockito.any(Employee.class));
    }

    @Test
    void testGetAllEmployees() throws Exception {
        Employee employee1 = new Employee("John", "Doe", new Date(), 3);
        employee1.setId(1L);
        Employee employee2 = new Employee("Jane", "Smith", new Date(), 4);
        employee2.setId(2L);

        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employee1, employee2));

        mockMvc.perform(get("/employee")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstname").value("John"))
                .andExpect(jsonPath("$[0].lastname").value("Doe"))
                .andExpect(jsonPath("$[0].skillLevel").value(3))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].firstname").value("Jane"))
                .andExpect(jsonPath("$[1].lastname").value("Smith"))
                .andExpect(jsonPath("$[1].skillLevel").value(4));

        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    void testGetAllEmployeesEmpty() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/employee")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No employees found."));

        verify(employeeService, times(1)).getAllEmployees();
    }
}
