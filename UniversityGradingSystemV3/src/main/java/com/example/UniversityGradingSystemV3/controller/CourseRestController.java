package com.example.UniversityGradingSystemV3.controller;

import com.example.UniversityGradingSystemV3.entity.Course;
import com.example.UniversityGradingSystemV3.service.CourseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor/courses")
public class CourseRestController {
    @Autowired
    private CourseService courseService;

    private boolean isDoctor(HttpSession session) {
        return "DOCTOR".equals(session.getAttribute("role"));
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(HttpSession session) {
        if (!isDoctor(session)) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody Course course, HttpSession session) {
        if (!isDoctor(session)) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable int id,
                                          @RequestBody Course updatedCourse,
                                          HttpSession session) {
        if (!isDoctor(session)) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(courseService.updateCourse(id, updatedCourse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable int id, HttpSession session) {
        if (!isDoctor(session)) return ResponseEntity.status(403).build();
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Deleted");
    }
}

