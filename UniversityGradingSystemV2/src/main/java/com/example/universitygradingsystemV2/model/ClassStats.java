package com.example.universitygradingsystemV2.model;

public class ClassStats {
    private String courseName;
    private int studentCount;
    private double avgGrade;
    private double medianGrade;
    private double minGrade;
    private double maxGrade;

    public ClassStats(String courseName, int studentCount, double avgGrade, double medianGrade, double minGrade, double maxGrade) {
        this.courseName = courseName;
        this.studentCount = studentCount;
        this.avgGrade = avgGrade;
        this.medianGrade = medianGrade;
        this.minGrade = minGrade;
        this.maxGrade = maxGrade;
    }

    public String getCourseName() { return courseName; }
    public int getStudentCount() { return studentCount; }
    public double getAvgGrade() { return avgGrade; }
    public double getMedianGrade() { return medianGrade; }
    public double getMinGrade() { return minGrade; }
    public double getMaxGrade() { return maxGrade; }
}
