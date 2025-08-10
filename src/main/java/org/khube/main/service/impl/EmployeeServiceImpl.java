package org.khube.main.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;
import org.khube.main.client.AddressClient;
import org.khube.main.client.DepartmentClient;
import org.khube.main.dto.AddressDto;
import org.khube.main.dto.DepartmentDto;
import org.khube.main.dto.EmployeeCreateDto;
import org.khube.main.dto.EmployeeDto;
import org.khube.main.entity.Employee;
import org.khube.main.exception.AddressNotFoundException;
import org.khube.main.exception.DepartmentNotFoundException;
import org.khube.main.mapper.EmployeeMapper;
import org.khube.main.repository.EmployeeRepository;
import org.khube.main.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AddressClient addressClient;
    private final DepartmentClient departmentClient;

    private static final String ADDRESS_SERVICE = "address-service";
    private static final String DEPARTMENT_SERVICE = "department-service";

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, AddressClient addressClient, DepartmentClient departmentClient) {
        this.employeeRepository = employeeRepository;
        this.addressClient = addressClient;
        this.departmentClient = departmentClient;
    }

    /**
     * Creates a new employee and saves it to the database.
     *
     * @param employeeCreateDto the DTO containing employee details
     * @return the created EmployeeDto
     * @throws IllegalArgumentException if employeeCreateDto is null
     */
    @Transactional(rollbackOn = Exception.class, value = Transactional.TxType.REQUIRED)
    @Override
    public EmployeeDto createEmployee(EmployeeCreateDto employeeCreateDto) {
        if (employeeCreateDto == null) {
            throw new IllegalArgumentException("Employee must not be null");
        }

        employeeCreateDto.setAddressId(getValidAddressId(employeeCreateDto));
        employeeCreateDto.setDepartmentId(getValidDeptIds(employeeCreateDto));

        Employee savedEmployee = employeeRepository.save(EmployeeMapper.mapToEntity(employeeCreateDto));
        return EmployeeMapper.mapToDto(savedEmployee);
    }

    /**
     * Validates and retrieves department IDs from the employee creation DTO.
     *
     * @param employeeCreateDto the DTO containing department IDs
     * @return a set of valid department IDs
     * @throws DepartmentNotFoundException if any department ID is invalid
     */
    private Set<Long> getValidDeptIds(EmployeeCreateDto employeeCreateDto) {
        Set<Long> validDeptIds = new HashSet<>();
        if (employeeCreateDto.getDepartmentId() != null && !employeeCreateDto.getDepartmentId().isEmpty()) {
            for (Long deptId : employeeCreateDto.getDepartmentId()) {
                DepartmentDto dept = departmentClient.getDepartmentById(deptId);
                System.out.println("Department: " + dept);
                if (dept == null) {
                    throw new DepartmentNotFoundException("Department not found for id: " + deptId);
                }
                validDeptIds.add(dept.getDepartmentId());
            }
        }
        return validDeptIds;
    }

    /**
     * Validates and retrieves address IDs from the employee creation DTO.
     *
     * @param employeeCreateDto the DTO containing address IDs
     * @return a set of valid address IDs
     * @throws AddressNotFoundException if any address ID is invalid
     */
    private Set<Long> getValidAddressId(EmployeeCreateDto employeeCreateDto) {
        Set<Long> validAddressId = new HashSet<>();
        if( employeeCreateDto.getAddressId() != null && !employeeCreateDto.getAddressId().isEmpty()) {
            for (Long addressId : employeeCreateDto.getAddressId()) {
                AddressDto address = getAddressById(addressId);
                System.out.println("Address: " + address);
                if (address == null) {
                    throw new AddressNotFoundException("Address not found for id: " + addressId);
                }
                validAddressId.add(address.getAddressId());
            }
        }
        return validAddressId;
    }

    /**
     * Retrieves an employee by their employee ID.
     *
     * @param empId the employee ID
     * @return an Optional containing the EmployeeDto if found, or empty if not found
     */
    @Override
    public Optional<EmployeeDto> getEmployeeByEmpId(Long empId) {
        if (empId == null) {
            return Optional.empty();
        }
        Optional<Employee> optionalEmployee = employeeRepository.findByEmpId(empId);
        return optionalEmployee.map(EmployeeMapper::mapToDto);
    }

    /**
     * Retrieves a department by its ID using a circuit breaker for resilience.
     *
     * @param departmentId the department ID
     * @return the DepartmentDto if found, or a fallback value if not found
     */
    @CircuitBreaker(name = DEPARTMENT_SERVICE, fallbackMethod = "departmentByIdFallback")
    public DepartmentDto getDepartmentById(Long departmentId) {
        return departmentClient.getDepartmentById(departmentId);
    }

    /**
     * Retrieves an address by its ID using a circuit breaker for resilience.
     *
     * @param addressId the address ID
     * @return the AddressDto if found, or a fallback value if not found
     */
    @CircuitBreaker(name = ADDRESS_SERVICE, fallbackMethod = "addressByIdFallback")
    public AddressDto getAddressById(Long addressId) {
        return addressClient.getAddressById(addressId);
    }

    /**
     * Fallback method for retrieving a department by ID when the circuit breaker is open.
     *
     * @param addressId the address ID
     * @return a fallback DepartmentDto
     */
    public DepartmentDto departmentByIdFallback(Long addressId) {
        DepartmentDto fallback = new DepartmentDto();
        fallback.setDepartmentId(addressId);
        fallback.setDepartmentName("Fallback Department");
        fallback.setEmail("Fallback Department Email");
        fallback.setLocation("Fallback Location");
        fallback.setNumberOfEmployees(100);
        fallback.setDescription("Fallback Description");
        return fallback;
    }

    /**
     * Fallback method for retrieving an address by ID when the circuit breaker is open.
     *
     * @param addressId the address ID
     * @return a fallback AddressDto
     */
    public AddressDto addressByIdFallback(Long addressId) {
        AddressDto fallback = new AddressDto();
        fallback.setAddressId(addressId);
        fallback.setStreet("Fallback Street");
        fallback.setLandmark("Fallback Landmark");
        fallback.setCity("Fallback City");
        fallback.setState("Fallback State");
        fallback.setCountry("Fallback Country");
        fallback.setPinCode(111111);
        return fallback;
    }

}
