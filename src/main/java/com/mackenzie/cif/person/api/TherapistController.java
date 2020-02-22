package com.mackenzie.cif.person.api;

import com.mackenzie.cif.person.domain.Therapist;
import com.mackenzie.cif.person.domain.TherapistService;
import com.mackenzie.cif.person.domain.dto.TherapistDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/therapist")
@Slf4j
public class TherapistController {

    @Autowired
    private TherapistService service;

    @GetMapping("/listAll")
    public ResponseEntity listAll(){
        log.info("List all Therapist >>>>> ");
        //List<TherapistDTO> therapists = new ArrayList<>();
        List<TherapistDTO> therapists = null;
        try{
            therapists = service.listAllTherapists();
        }catch (Exception e){
            log.error("Error while listing all therapist", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(therapists, HttpStatus.OK);
    }

}
