package com.example.UniversityGradingSystemV3.config;

import com.example.UniversityGradingSystemV3.entity.Doctor;
import com.example.UniversityGradingSystemV3.entity.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Check if the user is a doctor
        if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_DOCTOR"))) {
            Doctor doctor = (Doctor) userDetails; // We can safely cast here now
            request.getSession().setAttribute("doctor", doctor);
            request.getSession().setAttribute("role", "DOCTOR");
            response.sendRedirect("/doctor/dashboard");
        } else if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT"))) {
            Student student = (Student) userDetails; // Similarly, for student
            request.getSession().setAttribute("student", student);
            request.getSession().setAttribute("role", "STUDENT");
            response.sendRedirect("/student/dashboard");
        }
    }
}
