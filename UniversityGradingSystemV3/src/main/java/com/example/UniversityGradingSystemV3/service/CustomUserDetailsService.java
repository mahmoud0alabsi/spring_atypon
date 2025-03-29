package com.example.UniversityGradingSystemV3.service;

import com.example.UniversityGradingSystemV3.entity.Doctor;
import com.example.UniversityGradingSystemV3.entity.Student;
import com.example.UniversityGradingSystemV3.repository.DoctorRepository;
import com.example.UniversityGradingSystemV3.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Try finding doctor by username
        Optional<Doctor> doctorOpt = doctorRepo.findByUsername(username);
        if (doctorOpt.isPresent()) {
            Doctor doctor = doctorOpt.get();
            return doctor;
        }

        // Try finding student by studentId
        Optional<Student> studentOpt = studentRepo.findById(username);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            return student;
        }

        throw new UsernameNotFoundException("User not found");
    }
}

