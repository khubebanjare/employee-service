package org.khube.main.mapper.impl;

import org.khube.main.dto.request.EmployeeRequestDto;
import org.khube.main.dto.response.EmployeeResponseDto;
import org.khube.main.entity.Employee;
import org.khube.main.mapper.EmployeeMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    public Employee mapToEntity(EmployeeRequestDto employeeDto) {
        if (employeeDto == null) {
            return null;
        }

        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setAge(employeeDto.getAge());
        employee.setSal(employeeDto.getSal());
        return employee;
    }

    public EmployeeResponseDto mapToDto(Employee employee) {
        if (employee == null) {
            return null;
        }

        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
        employeeResponseDto.setEmpId(employee.getEmpId());
        employeeResponseDto.setFirstName(employee.getFirstName());
        employeeResponseDto.setLastName(employee.getLastName());
        employeeResponseDto.setAge(employee.getAge());
        employeeResponseDto.setSal(employee.getSal());
        return employeeResponseDto;
    }

}
