package com.example.universitygradingsystemV2.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/student_grading_system?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource dataSource;

    static {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found!", e);
        }
        config.setDriverClassName(DRIVER_CLASS);


        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        config.setMaximumPoolSize(10); // Max connections in the pool
        config.setMinimumIdle(2); // Min idle connections
        config.setIdleTimeout(30000); // 30 sec idle timeout
        config.setMaxLifetime(1800000); // 30 min max connection lifetime
        config.setConnectionTimeout(10000); // 10 sec connection timeout
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closePool() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
