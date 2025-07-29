package org.khube.main;

import org.junit.jupiter.api.Test;
import org.khube.main.repository.primary.EmployeeRepository;
import org.khube.main.service.EmployeeService;
import org.khube.main.service.kafka.KafkaMessagePublisher;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@ActiveProfiles("test")
class EmployeeServiceApplicationTests {

	@MockitoBean
	private EmployeeService employeeService;

	@MockitoBean
	private EmployeeRepository employeeRepository;

	@MockitoBean
	private KafkaMessagePublisher kafkaMessagePublisher;


	@Test
	void contextLoads() {
        // TODO document why this method is empty
    }

}
