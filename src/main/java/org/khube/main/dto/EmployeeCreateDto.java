package org.khube.main.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeCreateDto {

    @NotNull(message = "First name is required")
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
    private String firstName;

    @NotNull(message = "Last name is required")
    @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters")
    private String lastName;

    @NotNull(message = "Phone number is required")
    @Size(min = 10, max = 12, message = "Phone number must")
    private String mobileNumber;

    @NotNull(message = "Email is required")
    @Email(message = "Email should be valid", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;

    @NotNull(message = "Age is required")
    private Integer age;

    @NotNull(message = "Salary is required")
    @Min(value = 0, message = "Salary must be a positive number")
    private double sal;

    @NotNull(message = "Address ID is required")
    private Set<Long> addressId = new HashSet<>();

    @NotNull(message = "Department ID is required")
    private Set<Long> departmentId = new HashSet<>();
}
