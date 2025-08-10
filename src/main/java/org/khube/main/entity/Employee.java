package org.khube.main.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "EMPLOYEE_TAB")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee implements Serializable {

    @Id
    @Column(name = "EMP_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq", sequenceName = "EMPLOYEE_TAB_SEQ", allocationSize = 1)
    private Long empId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PHONE_NUMBER")
    private String mobileNumber;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "SAL")
    private Double sal;

    @ElementCollection
    @Column(name = "ADDRESS_ID")
    private Set<Long> addressId = new HashSet<>();

    @ElementCollection
    @Column(name = "DEPARTMENT_ID")
    private Set<Long> departmentId = new HashSet<>();

}