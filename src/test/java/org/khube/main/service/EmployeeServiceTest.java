package org.khube.main.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khube.main.dao.EmployeeDao;
import org.khube.main.entity.Employee;
import org.khube.main.service.impl.EmployeeServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void createEmployeeTest() {
        Employee employee = new Employee(null, "Khube", "Banjare", 30, 50000.0);
        Employee createdEmployee = new Employee(1L, "Khube", "Banjare", 30, 50000.0);

        when(employeeDao.createEmployee(any(Employee.class))).thenReturn(createdEmployee);

        Employee result = employeeService.createEmployee(employee);

        assertNotNull(result);
        assertEquals("Khube", result.getFirstName());
        assertEquals("Banjare", result.getLastName());
        assertEquals(30, result.getAge());
        assertEquals(50000.0, result.getSal());

        verify(employeeDao, times(1)).createEmployee(employee);
    }
}
