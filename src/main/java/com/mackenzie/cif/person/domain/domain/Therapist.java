package com.mackenzie.cif.person.domain.domain;

import com.mackenzie.cif.person.domain.dto.TherapistDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "Therapist")
public class Therapist {
    @Id
    private String id;

    private Person person;

    private Address address;

    private String crefito;
    private Boolean active;

    public void setAddress(Address address){
        this.address = address;
    }

    public void person(Person person){
        this.person = person;
    }

    public static Therapist create(TherapistDTO t){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(t, Therapist.class);
    }
}
