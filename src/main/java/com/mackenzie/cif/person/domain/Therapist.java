package com.mackenzie.cif.person.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Therapist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer therapistID;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String sex;
    private String telephoneNumber;
    private String publicPlace;
    private Integer houseNumber;
    private String city;
    private String state;
    private String postalCode;
    private String crefito;
}
