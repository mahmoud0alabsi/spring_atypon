package com.example.universitygradingsystemV1.service;

import com.example.universitygradingsystemV1.dao.GradeDAO;
import com.example.universitygradingsystemV1.model.Grade;

import java.util.List;

public class GradeService {
    private GradeDAO gradeDAO = GradeDAO.getInstance();

    private static GradeService _instance;

    public static GradeService getInstance() {
        if (_instance == null) _instance = new GradeService();
        return _instance;
    }

    private GradeService() {}

    public List<Grade> getStudentGrades(String studentId) {
        return gradeDAO.getGradesByStudentId(studentId);
    }
}

