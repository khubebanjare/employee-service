package org.khube.main.dao.impl;

import org.khube.main.dao.EmployeeDao;
import org.khube.main.entity.Employee;
import org.khube.main.repository.primary.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeDaoImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
