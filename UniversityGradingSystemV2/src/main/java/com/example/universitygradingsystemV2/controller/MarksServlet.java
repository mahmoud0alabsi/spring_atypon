package com.example.universitygradingsystemV2.controller;

import com.example.universitygradingsystemV2.model.Grade;
import com.example.universitygradingsystemV2.model.Student;
import com.example.universitygradingsystemV2.service.GradeService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/marks")
public class MarksServlet extends HttpServlet {
    private GradeService gradeService = GradeService.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");

        List<Grade> grades = gradeService.getStudentGradesWithStats(student.getStudentId());

        request.setAttribute("grades", grades);
        request.getRequestDispatcher("jsp/marks.jsp").forward(request, response);
    }
}

