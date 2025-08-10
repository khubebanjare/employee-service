package org.khube.main.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class DepartmentDto {
    private Long departmentId;
    private String departmentName;
    private String location;
    private Integer numberOfEmployees;
    private String contactNumber;
    private String email;
    private String description;
}
