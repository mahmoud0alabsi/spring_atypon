package com.example.UniversityGradingSystemV3.repository;

import com.example.UniversityGradingSystemV3.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}

