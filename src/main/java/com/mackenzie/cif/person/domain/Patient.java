package com.mackenzie.cif.person.domain;


import lombok.Data;


import javax.persistence.*;


@Entity
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientID;

    private Integer therapistID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String sex;
    private String telephoneNumber;
    private String publicPlace;
    private Integer houseNumber;
    private String neighborhood;
    private String city;
    private String state;
    private String postalCode;
    private String note;
}
