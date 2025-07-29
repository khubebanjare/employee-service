package org.khube.main.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeResponseDto {
    private Long empId;
    private String firstName;
    private String lastName;
    private Integer age;
    private Double sal;
}
