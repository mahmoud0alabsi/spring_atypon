package com.example.universitygradingsystemV2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

abstract public class MedianEquation {
    protected double getMedianGrade(Connection conn, String courseName) throws SQLException {
        String sql = "SELECT grade FROM grades g " +
                "JOIN courses c ON g.course_id = c.id " +
                "WHERE c.course_name = ? ORDER BY grade";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, courseName);
            ResultSet rs = stmt.executeQuery();

            List<Double> grades = new ArrayList<>();
            while (rs.next()) {
                grades.add(rs.getDouble("grade"));
            }

            if (grades.isEmpty()) {
                return 0;
            }

            int mid = grades.size() / 2;
            if (grades.size() % 2 == 0) {
                return (grades.get(mid - 1) + grades.get(mid)) / 2.0;
            } else {
                return grades.get(mid);
            }
        }
    }
}
