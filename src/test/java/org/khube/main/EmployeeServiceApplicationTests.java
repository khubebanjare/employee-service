package org.khube.main;

import org.junit.jupiter.api.Test;
import org.khube.main.dao.EmployeeDao;
import org.khube.main.repository.primary.EmployeeRepository;
import org.khube.main.service.EmployeeService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@ActiveProfiles("test")
class EmployeeServiceApplicationTests {

    @MockitoBean
    private EmployeeRepository employeeRepository;

    @MockitoBean
    private EmployeeDao employeeDao;

    @MockitoBean
    private EmployeeService employeeService;


	@Test
	void contextLoads() {
        // TODO document why this method is empty
    }

}
