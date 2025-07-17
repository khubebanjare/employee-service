package org.khube.main.entity.secondary;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ADDRESS_TABLE")
@Getter
@Setter
@ToString
public class Address {

    @Id
    @Column(name = "ADDRESS_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;

    @Column(name = "STREET")
    private String street;

    @Column(name = "LANDMARK")
    private String landmark;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "PIN_CODE")
    private Integer pinCode;
}
