package org.khube.main.service.impl;

import org.khube.main.dao.EmployeeDao;
import org.khube.main.entity.Employee;
import org.khube.main.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee must not be null");
        }
        return employeeDao.createEmployee(employee);
    }
}
