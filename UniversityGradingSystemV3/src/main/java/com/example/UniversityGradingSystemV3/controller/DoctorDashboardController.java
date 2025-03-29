package com.example.UniversityGradingSystemV3.controller;

import com.example.UniversityGradingSystemV3.entity.Doctor;
import com.example.UniversityGradingSystemV3.service.CourseService;
import com.example.UniversityGradingSystemV3.service.GradeService;
import com.example.UniversityGradingSystemV3.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doctor")
public class DoctorDashboardController {
    @Autowired private StudentService studentService;
    @Autowired private GradeService gradeService;
    @Autowired private CourseService courseService;

    @GetMapping("/dashboard")
    public String doctorDashboard(HttpSession session, Model model) {
        Doctor doctor = (Doctor) session.getAttribute("doctor");
        if (doctor == null) {
            return "redirect:/login";
        }
        model.addAttribute("doctor", doctor);
        return "doctor-dashboard";
    }

    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students-list";
    }

    @GetMapping("/courses")
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "courses-list";
    }

    @GetMapping("/students/{studentId}")
    public String viewStudentProfile(@PathVariable String studentId, Model model) {
        model.addAttribute("student", studentService.getStudentById(studentId).orElse(null));
        model.addAttribute("grades", gradeService.getGradesByStudent(studentId));
        return "student-profile";
    }
}

