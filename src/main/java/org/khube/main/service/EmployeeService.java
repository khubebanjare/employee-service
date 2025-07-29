package org.khube.main.service;

import org.khube.main.dto.request.EmployeeRequestDto;
import org.khube.main.dto.response.EmployeeResponseDto;

public interface EmployeeService {

    EmployeeResponseDto createEmployee(EmployeeRequestDto employee);
}
