package com.example.UniversityGradingSystemV3.service;

import com.example.UniversityGradingSystemV3.entity.Doctor;
import com.example.UniversityGradingSystemV3.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Doctor registerDoctor(Doctor doctor) {
        if (doctorRepository.findByUsername(doctor.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        return doctorRepository.save(doctor);
    }

    public Optional<Doctor> getDoctorByUsername(String username) {
        return doctorRepository.findByUsername(username);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor updateDoctor(Long id, Doctor updated) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctor.setEmail(updated.getEmail());
        doctor.setUsername(updated.getUsername());
        // Only update password if explicitly provided
        if (updated.getPassword() != null && !updated.getPassword().isBlank()) {
            doctor.setPassword(passwordEncoder.encode(updated.getPassword()));
        }
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}

