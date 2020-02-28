package com.mackenzie.cif.person.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TherapistRepository extends JpaRepository<Therapist, Integer> {
    Therapist findByCrefito(String crefito);
}
