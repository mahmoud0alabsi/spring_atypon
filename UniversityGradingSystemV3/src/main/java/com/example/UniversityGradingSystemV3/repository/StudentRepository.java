package com.example.UniversityGradingSystemV3.repository;

import com.example.UniversityGradingSystemV3.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findById(String id);
}
