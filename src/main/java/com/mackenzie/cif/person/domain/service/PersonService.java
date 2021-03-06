package com.mackenzie.cif.person.domain.service;

import com.mackenzie.cif.person.common.AES;
import com.mackenzie.cif.person.common.SendEmail;
import com.mackenzie.cif.person.domain.domain.Person;
import com.mackenzie.cif.person.domain.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PersonService {

    @Value("${secret.key}")
    private String KEY;

    @Autowired
    PersonRepository repository;

    public List<Person> listAllPatient() {
        return repository.findAllByPatientNotNullAndActiveIsTrue();
    }

    public List<Person> listAllTherapist() {
        return repository.findAllByPatientNullAndActiveIsTrue();
    }

    public void registerPerson(Person person) {
        person.setActive(true);
        person.setPassword(AES.encrypt(person.getPassword(), KEY));

        try {
            repository.save(person);
        } catch (DuplicateKeyException e) {
            log.error("Could not register person, email already registered");
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Could not register person!");
            log.error(e.getMessage());
            throw e;
        }

        if(person.getPatient() != null){
            String body =
                "Olá, " + person.getFirstName()+"\n" +
                "Você foi cadastrado no sistema Apliação CIF com sucesso!\n"+
                "Use seu CPF e a senha: "+ AES.decrypt(person.getPassword(),KEY) + " para realizar o login.\n" +
                "\n\nRecomendamos que após o login, seja feita a troca da senha, por questões de segurança"+
                "\n\nEste é um email automatico, por favor não responda";

            SendEmail.sendSimpleEmail(person.getEmail(),body);
        }
    }

    public Person findPersonById(String id) {
        Person person = null;
        try {
            person = repository.findByIdAndActiveIsTrue(id);
            if(person != null){
                person.setPassword(AES.decrypt(person.getPassword(), KEY));
            }

        } catch (Exception e) {
            log.error("Could not find person!");
            log.error(e.getMessage());
            throw e;
        }
        return person;
    }

    public Person updatePerson(Person person, String id) {
        Optional<Person> optional = repository.findById(id);

        if (optional.isPresent()) {
            Person db = optional.get();

            db.setId(person.getId());
            db.setBirthDate(person.getBirthDate());
            db.setFirstName(person.getFirstName());
            db.setLastName(person.getLastName());
            db.setEmail(person.getEmail());
            db.setSex(person.getSex());
            db.setTelephoneNumber(person.getTelephoneNumber());

            if (!db.getPassword().equals(person.getPassword())) {
                person.setPassword(AES.encrypt(person.getPassword(), KEY));
                db.setPassword(person.getPassword());
            }

            if (person.getPatient() != null) {
                db.getPatient().setTherapist(person.getPatient().getTherapist());
                db.getPatient().setNote(person.getPatient().getNote());
            }
            db.setAddress(person.getAddress());
            repository.save(db);
            return db;
        } else {
            throw new RuntimeException("Could not update registry");
        }
    }

    public Person updatePassword(String password, String cpf) {
        Person person = repository.findByCpf(cpf);

        if (person != null) {
            person.setPassword(AES.encrypt(password, KEY));
            repository.save(person);
            return person;
        } else {
            throw new RuntimeException("Could not update password");
        }
    }

    public void forgotPassword(String cpf){
        Person person = repository.findByCpf(cpf);

        if(person != null){
            String body = "Olá, " + person.getFirstName()+"\n" +
                    "Está é sua senha: "+ AES.decrypt(person.getPassword(),KEY) + "\n" +
                    "\nRecomendamos que após o login, seja feita a troca da senha, por questões de segurança"+
                    "\n\nEste é um email automatico, por favor não responda";
            try{
                log.error("Trying to send email");
                SendEmail.sendSimpleEmail(person.getEmail(),body);
            }catch (Exception e){
                log.error("Error while trying to send email");
                throw e;
            }
        }
    }

    public void inactivate(String id) {
        Optional<Person> optional = repository.findById(id);

        if (optional.isPresent()) {
            Person db = optional.get();
            db.setActive(false);
            repository.save(db);
        } else {
            throw new RuntimeException("Could not inactivate person");
        }
    }

    public Person reactivatePerson(String id) {
        Optional<Person> optional = repository.findById(id);
        if (optional.isPresent()) {
            Person db = optional.get();
            db.setActive(true);
            repository.save(db);
            return db;
        } else {
            throw new RuntimeException("Could not reactivate person");
        }
    }

    public Person login(String userLogin, String password) throws IllegalAccessException {
        log.info("Login service >>>>>");
        Boolean exist = null;

        log.info("encrypting password >>>>>");
        password = AES.encrypt(password, KEY);

        try {
            log.info("validating existence of person");
            if(userLogin.contains("@")){
                exist = repository.existsByEmailAndPassword(userLogin, password);
            }else{
                exist = repository.existsByCpfAndPassword(userLogin, password);
            }
        } catch (Exception e) {
            log.error("Error while validating userLogin and password");
            log.error(e.getMessage());
            throw e;
        }

        Person person = null;
        if (exist) {
            log.info("searching person on database");
            if(userLogin.contains("@")){
                person = repository.findByEmail(userLogin);
            }else{
                person = repository.findByCpf(userLogin);
            }
        } else {
            log.error("usuario ou senha incorretos");
            throw new IllegalAccessException("usuario ou senha incorretos");
        }
        return person;
    }

    public List<Person> findPatientsByTherapist(String id){
        List<Person> patients = null;
        try{
            patients = repository.findAllByPatientNotNullAndPatientTherapistId(id);
            patients.removeIf(patient -> !patient.getActive());
        }catch (Exception e){
            log.error("Erro while trying to list patients from therapist", e.getMessage());
            throw e;
        }
        return patients;
    }

    public Person findPersonByCPF(String cpf){
        Person person = null;
        try{
            person = repository.findByCpf(cpf);
        }catch (Exception e){
            log.error("Error while trying to find person by CPF", e.getMessage());
            throw e;
        }
        return person;
    }
}
