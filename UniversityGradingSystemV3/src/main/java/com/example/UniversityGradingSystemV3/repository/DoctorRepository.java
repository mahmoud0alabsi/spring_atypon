package com.example.UniversityGradingSystemV3.repository;

import com.example.UniversityGradingSystemV3.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByUsername(String username);
}

