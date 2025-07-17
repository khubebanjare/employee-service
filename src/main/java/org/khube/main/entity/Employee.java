package org.khube.main.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "EMPLOYEE_TAB")
@Getter
@Setter
@ToString
public class Employee {

    @Id
    @Column(name = "EMP_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long empId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "SAL")
    private Double sal;
}
