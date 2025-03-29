package com.example.UniversityGradingSystemV3.controller;

import com.example.UniversityGradingSystemV3.entity.Grade;
import com.example.UniversityGradingSystemV3.entity.Student;
import com.example.UniversityGradingSystemV3.service.GradeService;
import com.example.UniversityGradingSystemV3.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctor")
public class DoctorRestController {
    @Autowired private StudentService studentService;
    @Autowired private GradeService gradeService;

    @PostMapping("/students")
    public ResponseEntity<?> registerStudent(@RequestBody Student student, HttpSession session) {
        if (!"DOCTOR".equals(session.getAttribute("role"))) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(studentService.registerStudent(student));
    }

    @PostMapping("/grades")
    public ResponseEntity<?> addGrade(@RequestParam String studentId,
                                      @RequestParam int courseId,
                                      @RequestParam double grade,
                                      HttpSession session) {
        if (!"DOCTOR".equals(session.getAttribute("role"))) return ResponseEntity.status(403).build();
        Grade g = gradeService.addGrade(studentId, courseId, grade);
        return ResponseEntity.ok(g);
    }

    @PutMapping("/grades/{gradeId}")
    public ResponseEntity<?> updateGrade(@PathVariable Long gradeId,
                                         @RequestParam double newGrade,
                                         HttpSession session) {
        if (!"DOCTOR".equals(session.getAttribute("role"))) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(gradeService.updateGrade(gradeId, newGrade));
    }

    @DeleteMapping("/grades/{gradeId}")
    public ResponseEntity<?> deleteGrade(@PathVariable Long gradeId, HttpSession session) {
        if (!"DOCTOR".equals(session.getAttribute("role"))) return ResponseEntity.status(403).build();
        gradeService.deleteGrade(gradeId);
        return ResponseEntity.ok("Deleted");
    }
}

