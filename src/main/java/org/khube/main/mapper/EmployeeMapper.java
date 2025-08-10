package org.khube.main.mapper;


import org.khube.main.dto.EmployeeCreateDto;
import org.khube.main.dto.EmployeeDto;
import org.khube.main.dto.EmployeeUpdateDto;
import org.khube.main.entity.Employee;

public class EmployeeMapper {

    private EmployeeMapper() {
        // Private constructor to prevent instantiation
    }

    /**
     * Maps EmployeeCreateDto to Employee entity.
     *
     * @param employeeCreateDto the DTO to map
     * @return the mapped Employee entity
     */
    public static Employee mapToEntity(EmployeeCreateDto employeeCreateDto) {
        if (employeeCreateDto == null) {
            return null;
        }

        Employee employee = new Employee();
        employee.setFirstName(employeeCreateDto.getFirstName());
        employee.setLastName(employeeCreateDto.getLastName());
        employee.setMobileNumber(employeeCreateDto.getMobileNumber());
        employee.setEmail(employeeCreateDto.getEmail());
        employee.setAge(employeeCreateDto.getAge());
        employee.setSal(employeeCreateDto.getSal());
        employee.setAddressId(employeeCreateDto.getAddressId());
        employee.setDepartmentId(employeeCreateDto.getDepartmentId());
        return employee;
    }

    /**
     * Updates an existing Employee entity with values from EmployeeUpdateDto.
     *
     * @param dto the DTO containing new values
     * @param entity the existing Employee entity to update
     */
    public static void mapToUpdateEntity(EmployeeUpdateDto dto, Employee entity) {
        if (dto == null || entity == null) return;

        if (dto.getFirstName() != null) entity.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) entity.setLastName(dto.getLastName());
        if( dto.getMobileNumber() != null) entity.setMobileNumber(dto.getMobileNumber());
        if (dto.getEmail() != null) entity.setEmail(dto.getEmail());
        if (dto.getAge() != null) entity.setAge(dto.getAge());
        if (dto.getSal() != null) entity.setSal(dto.getSal());
        if (dto.getAddressId() != null) entity.setAddressId(dto.getAddressId());
        if (dto.getDepartmentId() != null) entity.setDepartmentId(dto.getDepartmentId());
    }

    /**
     * Maps Employee entity to EmployeeDto.
     *
     * @param employee the entity to map
     * @return the mapped EmployeeDto
     */
    public static EmployeeDto mapToDto(Employee employee) {
        if (employee == null) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmpId(employee.getEmpId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setMobileNumber(employee.getMobileNumber());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setAge(employee.getAge());
        employeeDto.setSal(employee.getSal());
        employeeDto.setAddressId(employee.getAddressId());
        employeeDto.setDepartmentId(employee.getDepartmentId());
        return employeeDto;
    }

}
