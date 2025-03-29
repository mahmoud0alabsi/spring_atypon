package com.example.universitygradingsystemV2.dao;

import com.example.universitygradingsystemV2.model.Student;
import com.example.universitygradingsystemV2.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    private static StudentDAO instance;

    public static StudentDAO getInstance() {
        if (instance == null) instance = new StudentDAO();
        return instance;
    }

    private StudentDAO() {}

    public Student getStudentByStudentId(String studentId) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM students WHERE student_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, studentId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Student(
                                rs.getString("student_id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getString("password")
                        );
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
