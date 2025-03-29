package com.example.universitygradingsystemV2.controller;

import com.example.universitygradingsystemV2.model.Student;
import com.example.universitygradingsystemV2.service.LoginService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private LoginService loginService =  LoginService.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String stuId = (String) session.getAttribute("student_id");

        if (stuId != null) {
            response.sendRedirect(request.getContextPath() + "/dashboard");
            return;
        }

        request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentID = request.getParameter("student_id");
        String password = request.getParameter("password");

        Student student = loginService.authenticate(studentID, password);
        if (student != null) {
            HttpSession session = request.getSession();
            session.setAttribute("student", student);
            response.sendRedirect(request.getContextPath() + "/dashboard");;
        } else {
            request.setAttribute("errorMessage", "Invalid student id or password.");
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
        }
    }
}
