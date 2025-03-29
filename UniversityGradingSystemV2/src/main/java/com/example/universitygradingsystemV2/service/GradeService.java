package com.example.universitygradingsystemV2.service;

import com.example.universitygradingsystemV2.dao.GradeDAO;
import com.example.universitygradingsystemV2.model.Grade;

import java.util.List;


public class GradeService {
    private GradeDAO gradeDAO = GradeDAO.getInstance();

    private static GradeService instance;

    public static GradeService getInstance() {
        if (instance == null) instance = new GradeService();
        return instance;
    }

    private GradeService() {}

    public List<Grade> getStudentGradesWithStats(String studentId) {
        return gradeDAO.getStudentGradesWithStats(studentId);
    }
}

