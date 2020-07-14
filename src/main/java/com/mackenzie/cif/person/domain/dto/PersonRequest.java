package com.mackenzie.cif.person.domain.dto;

import com.mackenzie.cif.person.domain.domain.Address;
import com.mackenzie.cif.person.domain.domain.ProfessionalData;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Document(collection = "Person")
public class PersonRequest {

    @Id
    private String id;


    private String firstName;

    private String lastName;
    
    private String profilePic;

    private LocalDateTime birthDate;

    @Email
    private String email;

    @Size(min = 6, max = 8)
    private String password;

    private String sex;

    private String telephoneNumber;

    private Address address;

    private ProfessionalData professionalData;

    private String cpf;

    private PatientRequest patient;

    private Boolean active;

    public void setAddress(Address address){
        this.address = address;
    }
}
