package com.mackenzie.cif.person.domain.dto;

import com.mackenzie.cif.person.domain.Patient;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class PatientDTO {
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

    public static PatientDTO create(Patient p){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(p, PatientDTO.class);
    }
}
