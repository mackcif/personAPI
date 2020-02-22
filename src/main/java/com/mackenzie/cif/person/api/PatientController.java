package com.mackenzie.cif.person.api;

import com.mackenzie.cif.person.domain.PatientService;
import com.mackenzie.cif.person.domain.dto.PatientDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/patient")
@Slf4j
public class PatientController {

    @Autowired
    private PatientService service;

    @GetMapping("/listAll")
    public ResponseEntity listAllPatients(){
        log.info("List all questions >>>>>");
        List<PatientDTO> patients = new ArrayList();
        try{
            patients = service.listAllPatients();
        }catch (Exception e){
            log.error("Error while listing all patients", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/findPatientById")
    public ResponseEntity findPatientById(@RequestParam Integer id){
        log.info("Find patient by id started >>>>>");

        PatientDTO patientDTO = null;
        try {
            patientDTO = service.findPatientById(id);
        }catch (Exception e){
            log.error("Error while finding patient with ID "+ id, e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(patientDTO == null){
            String notFound = "There's no patient with ID "+id;
            return new ResponseEntity(notFound,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(patientDTO,HttpStatus.OK);
    }
}
