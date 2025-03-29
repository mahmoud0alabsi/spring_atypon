package com.example.universitygradingsystemV1.service;

import com.example.universitygradingsystemV1.dao.CourseDAO;
import com.example.universitygradingsystemV1.model.Course;

import java.util.List;

public class CourseService {
    private CourseDAO courseDAO = CourseDAO.getInstance();

    private static CourseService _instance;

    public static CourseService getInstance() {
        if (_instance == null) _instance = new CourseService();
        return _instance;
    }

    private CourseService() {}

    public List<Course> getStudentCourses(String studentId) {
        return courseDAO.getCoursesByStudentId(studentId);
    }
}
