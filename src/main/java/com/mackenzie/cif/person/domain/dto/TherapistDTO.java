package com.mackenzie.cif.person.domain.dto;

import com.mackenzie.cif.person.domain.Therapist;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class TherapistDTO {
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

    public static TherapistDTO create(Therapist t){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(t, TherapistDTO.class);
    }
}
