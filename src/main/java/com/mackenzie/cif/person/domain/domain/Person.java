package com.mackenzie.cif.person.domain.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Document(collection = "Person")
public class Person {

    @Id
    private String id;

    private String cpf;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private LocalDateTime birthDate;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    private String sex;

    private String telephoneNumber;

    private Address address;

    private Patient patient;

    private Boolean active;

    public void setAddress(Address address){
        this.address = address;
    }
}
