//package org.khube.main.service;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.khube.main.dto.request.EmployeeRequestDto;
//import org.khube.main.entity.Employee;
//import org.khube.main.mapper.EmployeeMapper;
//import org.khube.main.repository.EmployeeRepository;
//import org.khube.main.service.impl.EmployeeServiceImpl;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class EmployeeServiceTest {
//
//    @Mock
//    private EmployeeRepository employeeRepository;
//
//    @Mock
//    private EmployeeMapper employeeMapper;
//
//    @InjectMocks
//    private EmployeeServiceImpl employeeService;
//
//
//    @Test
//    void createEmployeeTest() {
//        EmployeeRequestDto createdEmployee = createNewEmployee();
//
//        // Prepare the mocked entity
//        Employee mockEntity = new Employee();
//        mockEntity.setFirstName("Khube");
//        mockEntity.setLastName("Banjare");
//        mockEntity.setAge(30);
//        mockEntity.setSal(50000.0);
//
//        // Prepare the mocked response DTO
//        EmployeeResponseDto mockResponse = new EmployeeResponseDto();
//        mockResponse.setFirstName("Khube");
//        mockResponse.setLastName("Banjare");
//        mockResponse.setAge(30);
//        mockResponse.setSal(50000.0);
//
//        // Mock mapper calls
//        when(employeeMapper.mapToEntity(createdEmployee)).thenReturn(mockEntity);
//        when(employeeRepository.save(any(Employee.class))).thenReturn(mockEntity);
//        when(employeeMapper.mapToDto(mockEntity)).thenReturn(mockResponse);
//
//        EmployeeResponseDto result = employeeService.createEmployee(createdEmployee);
//
//        assertNotNull(result);
//        assertEquals("Khube", result.getFirstName());
//        assertEquals("Banjare", result.getLastName());
//        assertEquals(30, result.getAge());
//        assertEquals(50000.0, result.getSal());
//
//        verify(employeeRepository, times(1)).save(mockEntity);
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
