package org.khube.main.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khube.main.dao.impl.EmployeeDaoImpl;
import org.khube.main.entity.Employee;
import org.khube.main.repository.primary.EmployeeRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeDaoTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeDaoImpl employeeDao;

    @Test
    void testCreateEmployee() {
        Employee employee = new Employee(null, "Khube", "Banjare", 30, 50000.0);
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeDao.createEmployee(employee);

        assertNotNull(result);
        assertEquals("Khube", result.getFirstName());
        assertEquals("Banjare", result.getLastName());
        assertEquals(30, result.getAge());
        assertEquals(50000.0, result.getSal());

    }

}
