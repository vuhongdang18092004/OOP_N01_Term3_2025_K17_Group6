package com.example.servingwebcontent.model.service;

import com.example.servingwebcontent.model.entity.Patient;
import com.example.servingwebcontent.model.entity.UserRole;
import com.example.servingwebcontent.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void registerPatient(Patient patient) {
        if (patientRepository.existsByUsername(patient.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        patient.setRole(UserRole.PATIENT);

        patientRepository.save(patient);

        System.out.println("Đăng ký thành công cho: " + patient.getUsername());
    }

    public Patient findByUsername(String username) {
        return patientRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found: " + username));
    }

    public Optional<Patient> findOptionalByUsername(String username) {
        return patientRepository.findByUsername(username);
    }
}
