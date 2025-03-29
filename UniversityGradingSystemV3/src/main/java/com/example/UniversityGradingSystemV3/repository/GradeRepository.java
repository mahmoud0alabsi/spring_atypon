package com.example.UniversityGradingSystemV3.repository;

import com.example.UniversityGradingSystemV3.entity.Grade;
import com.example.UniversityGradingSystemV3.entity.Student;
import com.example.UniversityGradingSystemV3.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudent(Student student);
    List<Grade> findByCourse(Course course);
}

