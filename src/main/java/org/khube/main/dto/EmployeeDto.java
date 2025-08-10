package org.khube.main.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDto {

    private Long empId;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private Integer age;
    private Double sal;
    private Set<Long> addressId = new HashSet<>();
    private Set<Long> departmentId = new HashSet<>();
}
