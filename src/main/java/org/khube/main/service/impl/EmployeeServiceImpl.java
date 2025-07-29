package org.khube.main.service.impl;

import jakarta.transaction.Transactional;
import org.khube.main.dto.request.EmployeeRequestDto;
import org.khube.main.dto.response.EmployeeResponseDto;
import org.khube.main.entity.Employee;
import org.khube.main.mapper.EmployeeMapper;
import org.khube.main.repository.primary.EmployeeRepository;
import org.khube.main.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Transactional(rollbackOn = Exception.class, value = Transactional.TxType.REQUIRED)
    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto requestDto) {
        if (requestDto == null) {
            throw new IllegalArgumentException("Employee must not be null");
        }
        Employee savedEmployee = employeeRepository.save(employeeMapper.mapToEntity(requestDto));
        return employeeMapper.mapToDto(savedEmployee);
    }

    @Override
    public Optional<EmployeeResponseDto> getEmployeeByEmpId(Long empId) {
        if (empId == null) {
            return Optional.empty();
        }
        Optional<Employee> optionalEmployee = employeeRepository.findByEmpId(empId);
        return optionalEmployee.map(employeeMapper::mapToDto);
    }
}
