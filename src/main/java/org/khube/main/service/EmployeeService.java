package org.khube.main.service;

import org.khube.main.dto.request.EmployeeRequestDto;
import org.khube.main.dto.response.EmployeeResponseDto;

import java.util.Optional;

public interface EmployeeService {

    EmployeeResponseDto createEmployee(EmployeeRequestDto employee);

    Optional<EmployeeResponseDto> getEmployeeByEmpId(Long empId);
}
