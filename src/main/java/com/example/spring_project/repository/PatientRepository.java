package com.example.spring_project.repository;

import com.example.spring_project.entities.Patient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByNomContains(String firstName, Pageable pageable);
    int countByNomContains(String firstName);
}
