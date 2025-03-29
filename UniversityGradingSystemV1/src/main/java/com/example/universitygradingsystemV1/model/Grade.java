package com.example.universitygradingsystemV1.model;

public class Grade {
    private int id;
    private int studentId;
    private int courseId;
    private String courseName;
    private double grade;

    public Grade(int id, int studentId, int courseId, String courseName, double grade) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.grade = grade;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getGrade() { return grade; }
    public void setGrade(double grade) { this.grade = grade; }

}

