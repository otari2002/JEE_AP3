package com.example.spring_project.service;

import com.example.spring_project.entities.Patient;
import com.example.spring_project.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void addPatient(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public int getPatientsCount(String name) {
        return patientRepository.countByNomContains(name);
    }

    @Override
    public List<Patient> getPatients(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return patientRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Patient> searchPatients(String keyword, int page, int pageSize ) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return patientRepository.findByNomContains(keyword, pageable);
    }

    @Override
    public void deletePatient(Long patientId) {
        patientRepository.deleteById(patientId);
    }
}