package org.khube.main.entity.document;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "employee")
public class Employee {

    @Id
    private Long empId;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private Integer age;
    private Double sal;
    private Long addressId;
    private Long departmentId;
}
