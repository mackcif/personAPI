package com.mackenzie.cif.person.domain.domain;

import lombok.Data;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class Address {
    @NotNull
    private String publicPlace;

    @NotNull
    @Positive
    private Integer houseNumber;

    @NotNull
    private String neighborhood;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    @Size(min = 8,max = 8)
    private String postalCode;
}
