package com.example.universitygradingsystemV1.controller;


import com.example.universitygradingsystemV1.model.Student;
import com.example.universitygradingsystemV1.service.LoginService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthController {
    private LoginService loginService =  LoginService.getInstance();
    private StudentController studentController;
    private BufferedReader reader;
    private PrintWriter writer;

    public AuthController(BufferedReader reader, PrintWriter writer) {
        this.reader = reader;
        this.writer = writer;
        this.studentController = new StudentController(reader, writer);
    }

    public void login() {
        try {
            System.out.println("Start handle login request");
            writer.println("Enter Student ID: ");
            writer.flush(); writer.flush();
            System.out.println("Waiting for student ID....");
            String studentId = reader.readLine();

            writer.println("Enter password: ");
            writer.flush();
            System.out.println("Waiting for student password....");
            String password = reader.readLine();

            Student student = loginService.authenticate(studentId, password);
            if (student != null) {
                writer.println("Login successful! Welcome " + student.getName()); writer.flush();
                studentController.showStudentMenu(student);
            } else {
                writer.println("Invalid credentials."); writer.flush();
            }
        } catch (IOException e) {
            writer.println("Error during login."); writer.flush();
        }
    }

    public void showAuthMenu() {
        while (true) {
            try {
                writer.println("Authentication Menu:");
                writer.println("1. Login");
                writer.println("2. Exit");
                writer.println("Enter your choice: ");
                writer.flush();

                System.out.println("Waiting student choice...");

                String choice = reader.readLine();

                System.out.println("Student choice " + choice);

                if (choice == null) break;

                switch (choice) {
                    case "1":
                        System.out.println("Go to login");
                        login();
                        break;
                    case "2":
                        System.out.println("Goodbye");
                        writer.println("Goodbye!");
                        writer.flush();
                        return;
                    default:
                        System.out.println("Default branch");
                        writer.println("Invalid choice. Try again.");
                        writer.flush();
                }
            } catch (IOException e) {
                writer.println("Error reading input. Disconnecting.");
                writer.flush();
                break;
            }
        }
    }
}
