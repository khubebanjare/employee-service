package org.khube.main.integration;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.khube.main.entity.Employee;
import org.khube.main.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeServiceIntegrationTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    @Order(1)
    void testSaveEmployee() {
        Employee createEmployee = createNewEmployee();

        Employee savedEmployee = employeeService.createEmployee(createEmployee);

        assertNotNull(savedEmployee);
        assertNotNull(savedEmployee.getEmpId());
        assertEquals("Khube", savedEmployee.getFirstName());
        assertEquals("Banjare", savedEmployee.getLastName());
        assertEquals(30, savedEmployee.getAge());
        assertEquals(50000.0, savedEmployee.getSal());

    }

    private Employee createNewEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Khube");
        employee.setLastName("Banjare");
        employee.setAge(30);
        employee.setSal(50000.0);
        return employee;
    }
}
