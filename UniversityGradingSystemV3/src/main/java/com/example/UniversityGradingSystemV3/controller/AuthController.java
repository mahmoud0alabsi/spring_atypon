package com.example.UniversityGradingSystemV3.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class AuthController {
    @GetMapping("/login")
    public String loginPage(HttpSession session) {
        if (session.getAttribute("role") != null) {
            String role = (String) session.getAttribute("role");
            if ("DOCTOR".equals(role)) {
                return "redirect:/doctor/dashboard";
            } else if ("STUDENT".equals(role)) {
                return "redirect:/student/dashboard";
            }
        }
        return "login";
    }
}