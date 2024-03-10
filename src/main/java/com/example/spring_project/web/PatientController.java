package com.example.spring_project.web;

import com.example.spring_project.entities.Patient;
import com.example.spring_project.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor

public class PatientController {
    private PatientService patientService;

    @GetMapping("/patients")
    public String index(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(name="keyword", defaultValue = "") String keyword) {
        List<Patient> patients;
        patients = patientService.searchPatients(keyword, page, 10);
        int patientsCount = patientService.getPatientsCount(keyword);
        int totalPages = (int) Math.ceil((double) patientsCount / 10);
        model.addAttribute("patients", patients);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("keyword", keyword);
        return "patients";
    }

    @GetMapping("/delete")
    public String deletePatient(Long id, String keyword, int page) {
        patientService.deletePatient(id);
        return "redirect:/patients?page="+page+"&keyword="+keyword;
    }
}