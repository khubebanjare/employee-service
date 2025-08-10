//package org.khube.main.integration;
//
//
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.khube.main.dto.request.EmployeeRequestDto;
//import org.khube.main.service.EmployeeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class EmployeeServiceIntegrationTest {
//
//    @Autowired
//    private EmployeeService employeeService;
//
//    @Test
//    @Order(1)
//    void testSaveEmployee() {
//        EmployeeRequestDto createdEmployee = createNewEmployee();
//
//        EmployeeResponseDto createdEmployeeResponseDto = employeeService.createEmployee(createdEmployee);
//
//        assertNotNull(createdEmployeeResponseDto);
//        assertNotNull(createdEmployeeResponseDto.getEmpId());
//        assertEquals("Khube", createdEmployeeResponseDto.getFirstName());
//        assertEquals("Banjare", createdEmployeeResponseDto.getLastName());
//        assertEquals(30, createdEmployeeResponseDto.getAge());
//        assertEquals(50000.0, createdEmployeeResponseDto.getSal());
//
//    }
//
//    private EmployeeRequestDto createNewEmployee() {
//        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto();
//        employeeRequestDto.setFirstName("Khube");
//        employeeRequestDto.setLastName("Banjare");
//        employeeRequestDto.setAge(30);
//        employeeRequestDto.setSal(50000.0);
//        return employeeRequestDto;
//    }
//}
