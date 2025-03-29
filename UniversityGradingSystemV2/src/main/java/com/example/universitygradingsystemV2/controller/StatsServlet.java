package com.example.universitygradingsystemV2.controller;

import com.example.universitygradingsystemV2.model.ClassStats;
import com.example.universitygradingsystemV2.model.Grade;
import com.example.universitygradingsystemV2.model.Student;
import com.example.universitygradingsystemV2.service.CourseService;
import com.example.universitygradingsystemV2.service.GradeService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/stats")
public class StatsServlet extends HttpServlet {
    private CourseService courseService = CourseService.getInstance();
    private GradeService gradeService = GradeService.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");

        List<ClassStats> classStats = courseService.getAllClassStats();

        request.setAttribute("classStats", classStats);

        request.getRequestDispatcher("jsp/stats.jsp").forward(request, response);
    }
}

