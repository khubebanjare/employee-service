package org.khube.main.controller;

import org.junit.jupiter.api.Test;
import org.khube.main.controler.EmployeeController;
import org.khube.main.dto.request.EmployeeRequestDto;
import org.khube.main.dto.response.EmployeeResponseDto;
import org.khube.main.service.EmployeeService;
import org.khube.main.service.kafka.KafkaEmployeeProducerPublisher;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;  // ✅ Mocked

    @MockitoBean
    private KafkaEmployeeProducerPublisher kafkaMEmployeeProducerPublisher;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    void testCreateEmployee() throws Exception {
        EmployeeRequestDto requestDto = new EmployeeRequestDto();
        requestDto.setFirstName("Khube");
        requestDto.setLastName("Banjare");
        requestDto.setAge(30);
        requestDto.setSal(50000.0);

        EmployeeResponseDto mockResponse = new EmployeeResponseDto();
        mockResponse.setEmpId(101L);
        mockResponse.setFirstName("Khube");
        mockResponse.setLastName("Banjare");
        mockResponse.setAge(30);
        mockResponse.setSal(50000.0);

        when(employeeService.createEmployee(any(EmployeeRequestDto.class))).thenReturn(mockResponse);

        mockMvc.perform(post("/api/employee/create")
                        .contentType("application/json")
                        .content("""
                        {
                          "firstName": "Khube",
                          "lastName": "Banjare",
                          "age": 30,
                          "sal": 50000.0
                        }
                        """))
                .andDo(print()) // 👈 Output check करें
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.empId").value(101))
                .andExpect(jsonPath("$.firstName").value("Khube"));
    }

    /*@Test
    void shouldReturnEmployeeWhenPresent() {
        // Given
        Long empId = 5L;
        EmployeeResponseDto mockDto = new EmployeeResponseDto();
        mockDto.setFirstName("Khube");
        mockDto.setLastName("Banjare");

        when(employeeService.getEmployeeByEmpId(empId)).thenReturn(Optional.of(mockDto));

        // When
        ResponseEntity<EmployeeResponseDto> response = employeeController.fetchEmployeeById(empId);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(mockDto);
    }

    @Test
    void shouldReturnBadRequestWhenOptionalIsEmpty() {
        // Given
        Long empId = 2L;
        when(employeeService.getEmployeeByEmpId(empId)).thenReturn(Optional.empty());

        // When
        ResponseEntity<EmployeeResponseDto> response = employeeController.fetchEmployeeById(empId);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }*/

    @Test
    void shouldReturnInternalServerErrorWhenExceptionOccurs() {
        // Given
        Long empId = 3L;
        when(employeeService.getEmployeeByEmpId(empId)).thenThrow(new RuntimeException("DB error"));

        // When
        ResponseEntity<EmployeeResponseDto> response = employeeController.fetchEmployeeById(empId);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
