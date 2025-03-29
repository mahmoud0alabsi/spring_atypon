package com.example.universitygradingsystemV2.service;

import com.example.universitygradingsystemV2.dao.CourseDAO;
import com.example.universitygradingsystemV2.model.ClassStats;

import java.util.List;

public class CourseService {
    private static CourseService instance;
    private CourseDAO courseDAO = CourseDAO.getInstance();

    private CourseService() {}

    public static synchronized CourseService getInstance() {
        if (instance == null) {
            instance = new CourseService();
        }
        return instance;
    }

    public List<ClassStats> getAllClassStats() {
        return courseDAO.getClassStats();
    }
}
