package com.example.universitygradingsystemV1.service;

import com.example.universitygradingsystemV1.dao.StudentDAO;
import com.example.universitygradingsystemV1.model.Student;
import com.example.universitygradingsystemV1.utils.PasswordHasher;

public class LoginService {
    private StudentDAO studentDAO = StudentDAO.getInstance();

    private static LoginService _instance;

    public static LoginService getInstance() {
        if (_instance == null) _instance = new LoginService();
        return _instance;
    }

    private LoginService() {}

    public Student authenticate(String student_id, String password) {
        Student student = studentDAO.getStudentByStudentId(student_id);
        if (student != null && PasswordHasher.verifyPassword(password, student.getPassword())) {
            return student;
        }
        return null;
    }
}

