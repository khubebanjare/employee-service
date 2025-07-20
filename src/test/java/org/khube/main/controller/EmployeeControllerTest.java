package org.khube.main.controller;

import org.junit.jupiter.api.Test;
import org.khube.main.controler.EmployeeController;
import org.khube.main.entity.Employee;
import org.khube.main.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Test
    void testCreateEmployee() throws Exception {
        Employee createdEmployee = new Employee(1L, "Khube", "Banjare", 30, 50000.0);

        when(employeeService.createEmployee(any(Employee.class))).thenReturn(createdEmployee);

        mockMvc.perform(post("/employee/create")
                .contentType("application/json")
                .content("{\"firstName\":\"Khube\",\"lastName\":\"Banjare\",\"age\":30,\"salary\":50000.0}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.empId").value(1L))
                .andExpect(jsonPath("$.firstName").value("Khube"))
                .andExpect(jsonPath("$.lastName").value("Banjare"))
                .andExpect(jsonPath("$.age").value(30))
                .andExpect(jsonPath("$.sal").value(50000.0));

        verify(employeeService).createEmployee(any(Employee.class));
    }
}
