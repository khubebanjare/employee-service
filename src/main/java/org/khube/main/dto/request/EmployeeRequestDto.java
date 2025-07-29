package org.khube.main.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeRequestDto {
    private String firstName;
    private String lastName;
    private Integer age;
    private Double sal;
}
