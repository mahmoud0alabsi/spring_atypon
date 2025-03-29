package com.example.universitygradingsystemV2.model;

public class Grade {
    private int id;
    private int studentId;
    private int courseId;
    private String courseName;
    private double grade;
    private double avgGrade;
    private double medianGrade;
    private double minGrade;
    private double maxGrade;

    public Grade(int id, int studentId, int courseId, String courseName, double grade) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.grade = grade;
        this.avgGrade = 0;
        this.medianGrade = 0;
        this.minGrade = 0;
        this.maxGrade = 0;
    }

    public Grade(int id, int studentId, int courseId, String courseName, double grade, double avgGrade, double medianGrade, double minGrade, double maxGrade) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.grade = grade;
        this.avgGrade = avgGrade;
        this.medianGrade = medianGrade;
        this.minGrade = minGrade;
        this.maxGrade = maxGrade;
    }

    public int getId() { return id; }

    public int getStudentId() { return studentId; }

    public int getCourseId() { return courseId; }

    public String getCourseName() {
        return courseName;
    }

    public double getGrade() { return grade; }

    public double getAvgGrade() {
        return avgGrade;
    }

    public double getMedianGrade() {
        return medianGrade;
    }

    public double getMinGrade() {
        return minGrade;
    }

    public double getMaxGrade() {
        return maxGrade;
    }
}

