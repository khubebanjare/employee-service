package org.khube.main.service;

import org.khube.main.dto.EmployeeCreateDto;
import org.khube.main.dto.EmployeeDto;

import java.util.Optional;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeCreateDto employeeCreateDto);

    Optional<EmployeeDto> getEmployeeByEmpId(Long empId);
}
