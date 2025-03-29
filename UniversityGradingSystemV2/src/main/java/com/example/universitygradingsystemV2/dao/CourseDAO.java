package com.example.universitygradingsystemV2.dao;

import com.example.universitygradingsystemV2.model.ClassStats;
import com.example.universitygradingsystemV2.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO extends MedianEquation {
    private static CourseDAO instance;

    public static CourseDAO getInstance() {
        if (instance == null) instance = new CourseDAO();
        return instance;
    }

    private CourseDAO() {}

    public List<ClassStats> getClassStats() {
        List<ClassStats> stats = new ArrayList<>();
        String sql = "SELECT c.course_name, COUNT(g.student_id) AS student_count, " +
                "AVG(g.grade) AS avg_grade, " +
                "MIN(g.grade) AS min_grade, " +
                "MAX(g.grade) AS max_grade " +
                "FROM courses c " +
                "LEFT JOIN grades g ON c.id = g.course_id " +
                "GROUP BY c.course_name";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                double median = getMedianGrade(conn, rs.getString("course_name"));

                stats.add(new ClassStats(
                        rs.getString("course_name"),
                        rs.getInt("student_count"),
                        rs.getDouble("avg_grade"),
                        median,
                        rs.getDouble("min_grade"),
                        rs.getDouble("max_grade")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stats;
    }
}

