package com.example.universitygradingsystemV1.controller;


import com.example.universitygradingsystemV1.model.Course;
import com.example.universitygradingsystemV1.model.Grade;
import com.example.universitygradingsystemV1.model.Student;
import com.example.universitygradingsystemV1.service.CourseService;
import com.example.universitygradingsystemV1.service.GradeService;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class StudentController {
    private CourseService courseService = CourseService.getInstance();
    private GradeService gradeService = GradeService.getInstance();
    private BufferedReader reader;
    private PrintWriter writer;

    public StudentController(BufferedReader reader, PrintWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void showStudentMenu(Student student) {
        while (true) {
            try {
                writer.println("\nWelcome, " + student.getName());
                writer.println("1. View Grades");
                writer.println("2. View My Classes");
                writer.println("3. View My Information");
                writer.println("4. Logout");
                writer.println("Enter your choice: ");
                writer.flush();

                String choice = reader.readLine();

                if (choice == null) break;

                switch (choice) {
                    case "1":
                        viewGrades(student.getStudentId());
                        break;
                    case "2":
                        viewSchedule(student.getStudentId());
                        break;
                    case "3":
                        viewStudentInfo(student);
                        break;
                    case "4":
                        writer.println("Logging out...");
                        return;
                    default:
                        writer.println("Invalid choice. Try again.");
                }
            } catch (IOException e) {
                writer.println("Error reading input.");
                break;
            }
        }
    }

    private void viewGrades(String studentId) {
        List<Grade> grades = gradeService.getStudentGrades(studentId);
        if (grades.isEmpty()) {
            writer.println("No grades found.");
        } else {
            writer.println("\nYour Grades:");
            for (Grade grade : grades) {
                writer.println("Course: " + grade.getCourseName() + " | Grade: " + grade.getGrade());
            }
        }
    }

    private void viewSchedule(String studentId) {
        List<Course> courses = courseService.getStudentCourses(studentId);
        if (courses.isEmpty()) {
            writer.println("No registered courses.");
        } else {
            int totalCredits = 0;
            writer.println("\nYour Courses:");
            for (Course course : courses) {
                writer.println("Course: " + course.getCourseName() + " | Credits: " + course.getCourseCredits());
                totalCredits += course.getCourseCredits();
            }
            writer.println("Total Credits: " + totalCredits);
        }
    }

    private void viewStudentInfo(Student student) {
        writer.println("\nStudent Information:");
        writer.println("Student ID: " + student.getStudentId());
        writer.println("Name: " + student.getName());
        writer.println("Email: " + student.getEmail());
    }
}

