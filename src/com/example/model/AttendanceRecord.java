package com.example.model;

public class AttendanceRecord {
    private int id;
    private int studentId;
    private int courseId;
    private String date;
    private boolean present;

    // getters&setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public boolean isPresent() { return present; }
    public void setPresent(boolean present) { this.present = present; }
}

