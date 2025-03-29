package com.example.UniversityGradingSystemV3.service;

import com.example.UniversityGradingSystemV3.entity.Course;
import com.example.UniversityGradingSystemV3.entity.Grade;
import com.example.UniversityGradingSystemV3.entity.Student;
import com.example.UniversityGradingSystemV3.repository.CourseRepository;
import com.example.UniversityGradingSystemV3.repository.GradeRepository;
import com.example.UniversityGradingSystemV3.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Grade> getGradesByStudent(String studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return gradeRepository.findByStudent(student);
    }

    public List<Grade> getGradesByCourse(int courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return gradeRepository.findByCourse(course);
    }

    public Grade addGrade(String studentId, int courseId, double gradeValue) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Grade grade = new Grade();
        grade.setStudent(student);
        grade.setCourse(course);
        grade.setGrade(gradeValue);

        return gradeRepository.save(grade);
    }

    public Grade updateGrade(Long gradeId, double newGrade) {
        Grade grade = gradeRepository.findById(gradeId)
                .orElseThrow(() -> new RuntimeException("Grade not found"));
        grade.setGrade(newGrade);
        return gradeRepository.save(grade);
    }

    public void deleteGrade(Long gradeId) {
        if (!gradeRepository.existsById(gradeId)) {
            throw new RuntimeException("Grade not found");
        }
        gradeRepository.deleteById(gradeId);
    }
}

