package com.mackenzie.cif.person.api;

import com.mackenzie.cif.person.domain.PatientService;
import com.mackenzie.cif.person.domain.dto.PatientDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/patient")
@Slf4j
public class PatientController {

    @Autowired
    private PatientService service;

    @GetMapping("/listAll")
    public ResponseEntity listAllPatients(@RequestParam @Nullable Integer page, @RequestParam @Nullable Integer pageSize){
        log.info("List all questions >>>>>");
        List<PatientDTO> patients = null;
        try{
            if(page == null && pageSize != null){
                log.info("Received final index {}", pageSize);
                patients = service.listAllPatients(0,pageSize);
            }else if(page != null && pageSize != null){
                log.info("Received both index: "+ page + "and " +  pageSize);
                patients = service.listAllPatients(page,pageSize);
            }else {
                patients = service.listAllPatients();
            }
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
