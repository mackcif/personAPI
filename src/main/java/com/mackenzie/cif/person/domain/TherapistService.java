package com.mackenzie.cif.person.domain;

import com.mackenzie.cif.person.domain.dto.TherapistDTO;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TherapistService {
    @Autowired
    private TherapistRepository rep;
    public List<TherapistDTO> listAllTherapists(){
        log.info("Service list all therapists");
        return rep.findAll().stream().map(TherapistDTO::create).collect(Collectors.toList());
    }

    public TherapistDTO findByCode(String code){
        log.info("Service find by code >>>>>");
        Therapist therapist = rep.findByCrefito(code);
        if(therapist == null){
            return null;
        }
        TherapistDTO therapistDTO = TherapistDTO.create(therapist);
        return therapistDTO;
    }



}
