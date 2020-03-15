package com.mackenzie.cif.person.domain.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Patient")
public class Patient {

    @Id
    private String id;

    private Person person;

    private Address address;

    private String therapist_id;
    private String note;

    private Boolean active;

    public void setAddress(Address address){
        this.address = address;
    }

    public void person(Person person){
        this.person = person;
    }
}
