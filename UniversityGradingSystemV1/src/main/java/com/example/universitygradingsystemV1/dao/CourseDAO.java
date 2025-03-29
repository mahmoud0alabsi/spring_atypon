package com.example.universitygradingsystemV1.dao;

import com.example.universitygradingsystemV1.model.Course;
import com.example.universitygradingsystemV1.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    private static CourseDAO _instance;

    public static CourseDAO getInstance() {
        if (_instance == null) _instance = new CourseDAO();
        return _instance;
    }

    private CourseDAO() {}

    public List<Course> getCoursesByStudentId(String studentId) {
        List<Course> courses = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()){
            String query = "SELECT c.id, c.course_name, c.course_credits " +
                    "FROM courses c " +
                    "JOIN grades g ON c.id = g.course_id " +
                    "WHERE g.student_id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, studentId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        courses.add(new Course(
                                rs.getInt("id"),
                                rs.getString("course_name"),
                                rs.getInt("course_credits")
                        ));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
}

