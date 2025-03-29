package com.example.UniversityGradingSystemV3.controller;

import com.example.UniversityGradingSystemV3.entity.Grade;
import com.example.UniversityGradingSystemV3.entity.Student;
import com.example.UniversityGradingSystemV3.service.GradeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentDashboardController {
    @Autowired
    private GradeService gradeService;

    @GetMapping("/dashboard")
    public String studentDashboard(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) return "redirect:/login";
        List<Grade> grades = gradeService.getGradesByStudent(student.getStudentId());
        model.addAttribute("grades", grades);
        model.addAttribute("student", student);
        return "student-dashboard";
    }
}

