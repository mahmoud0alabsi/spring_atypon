package com.example.universitygradingsystemV1.dao;

import com.example.universitygradingsystemV1.model.Grade;
import com.example.universitygradingsystemV1.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO {
    private static GradeDAO _instance;

    public static GradeDAO getInstance() {
        if (_instance == null) _instance = new GradeDAO();
        return _instance;
    }

    private GradeDAO() {}

    public List<Grade> getGradesByStudentId(String studentId) {
        List<Grade> grades = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {

            String query = "SELECT g.id, g.student_id, g.course_id, g.grade, c.course_name " +
                    "FROM grades g " +
                    "JOIN courses c ON g.course_id = c.id " +
                    "WHERE g.student_id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, studentId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Grade grade = new Grade(
                                rs.getInt("id"),
                                rs.getInt("student_id"),
                                rs.getInt("course_id"),
                                rs.getString("course_name"),
                                rs.getDouble("grade")
                        );
                        grades.add(grade);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return grades;
    }
}

