package com.example.universitygradingsystemV2.dao;

import com.example.universitygradingsystemV2.model.Grade;
import com.example.universitygradingsystemV2.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO extends MedianEquation {
    private static GradeDAO instance;

    public static GradeDAO getInstance() {
        if (instance == null) instance = new GradeDAO();
        return instance;
    }

    private GradeDAO() {}

    public List<Grade> getStudentGradesWithStats(String studentId) {
        List<Grade> studentGradesWithStats = new ArrayList<>();
        String sql = "SELECT c.course_name, g.id, g.student_id, g.course_id, g.grade, " +
                "(SELECT AVG(g2.grade) FROM grades g2 WHERE g2.course_id = c.id) AS avg_grade, " +
                "(SELECT MIN(g3.grade) FROM grades g3 WHERE g3.course_id = c.id) AS min_grade, " +
                "(SELECT MAX(g4.grade) FROM grades g4 WHERE g4.course_id = c.id) AS max_grade " +
                "FROM courses c " +
                "JOIN grades g ON c.id = g.course_id " +
                "WHERE g.student_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Calculate median separately
                double median = getMedianGrade(conn, rs.getString("course_name"));

                studentGradesWithStats.add(new Grade(
                        rs.getInt("id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getDouble("grade"),
                        rs.getDouble("avg_grade"),
                        median,
                        rs.getDouble("min_grade"),
                        rs.getDouble("max_grade")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentGradesWithStats;
    }
}

