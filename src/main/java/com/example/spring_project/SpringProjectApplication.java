package com.example.spring_project;

import com.example.spring_project.entities.Patient;
import com.example.spring_project.service.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringProjectApplication implements CommandLineRunner {
    @Autowired
    private PatientServiceImpl patService;

    public static void main(String[] args) {
        SpringApplication.run(SpringProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Stream.of("Yassine","Omar","Oussama","Anas","Yassine","Omar","Oussama","Anas","Yassine","Omar","Oussama","Anas","Yassine","Omar","Oussama","Anas","Yassine","Omar","Oussama","Anas","Yassine","Omar","Oussama","Anas","Yassine","Omar","Oussama","Anas","Yassine","Omar","Oussama","Anas","Yassine","Omar","Oussama","Anas","Yassine","Omar","Oussama","Anas","Yassine","Omar","Oussama","Anas").forEach(name->{
            Patient pat = new Patient();
            pat.setNom(name);
            pat.setMalade(name.equals("Anas"));
            pat.setDateNaissance(new Date());
            pat.setScore(10);
            patService.addPatient(pat);
        });
    }
}
