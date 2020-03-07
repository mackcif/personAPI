package com.mackenzie.cif.person.domain.domain;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Person {

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
    @Size(min = 6, max = 8)
    private String password;

    private String sex;

    private String telephoneNumber;
}
