package com.example.universitygradingsystemV1.model;

public class Course {
    private int id;
    private String courseName;
    private int courseCredits;

    public Course(int id, String courseName, int courseCredits) {
        this.id = id;
        this.courseName = courseName;
        this.courseCredits = courseCredits;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseCredits() {
        return courseCredits;
    }

    public void setCourseCredits(int courseCredits) {
        this.courseCredits = courseCredits;
    }
}

