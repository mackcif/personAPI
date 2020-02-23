package com.mackenzie.cif.person.domain.dto;

import com.mackenzie.cif.person.domain.Adress;
import com.mackenzie.cif.person.domain.Patient;
import com.mackenzie.cif.person.domain.Person;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class PatientDTO {
    private Integer patientID;
    private Integer therapistID;
    private Person person;
    private Adress adress;
    private String note;

    public static PatientDTO create(Patient p){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(p, PatientDTO.class);
    }
}
