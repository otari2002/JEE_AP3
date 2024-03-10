package com.example.spring_project.service;

import com.example.spring_project.entities.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PatientService {
    void addPatient(Patient patient);
    List<Patient> getAllPatients();
    int getPatientsCount(String name);
    List<Patient> getPatients(int page, int pageSize);
    List<Patient> searchPatients(String keyword, int page, int pageSize);
    void deletePatient(Long patientId);
}