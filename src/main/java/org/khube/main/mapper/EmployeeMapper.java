package org.khube.main.mapper;

import org.khube.main.dto.request.EmployeeRequestDto;
import org.khube.main.dto.response.EmployeeResponseDto;
import org.khube.main.entity.Employee;

public interface EmployeeMapper {

    Employee mapToEntity(EmployeeRequestDto employeeDto);

    EmployeeResponseDto mapToDto(Employee employee);
}
