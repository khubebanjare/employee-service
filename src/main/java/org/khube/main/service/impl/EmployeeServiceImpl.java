package org.khube.main.service.impl;

import org.khube.main.dto.request.EmployeeRequestDto;
import org.khube.main.dto.response.EmployeeResponseDto;
import org.khube.main.entity.Employee;
import org.khube.main.mapper.EmployeeMapper;
import org.khube.main.repository.primary.EmployeeRepository;
import org.khube.main.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto requestDto) {
        if (requestDto == null) {
            throw new IllegalArgumentException("Employee must not be null");
        }
        Employee savedEmployee = employeeRepository.save(employeeMapper.mapToEntity(requestDto));
        return employeeMapper.mapToDto(savedEmployee);
    }
}
