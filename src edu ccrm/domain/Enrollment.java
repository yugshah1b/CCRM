package edu.ccrm.domain;

import java.time.LocalDateTime;

import edu.ccrm.enums.Grade;

public class Enrollment {
    private final String studentId;
    private final String courseCode;
    private Grade grade; // nullable until assigned
    private final LocalDateTime enrolledAt = LocalDateTime.now();

    public Enrollment(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    public String getStudentId() { return studentId; }
    public String getCourseCode() { return courseCode; }
    public Grade getGrade() { return grade; }
    public void setGrade(Grade grade) { this.grade = grade; }
    public LocalDateTime getEnrolledAt() { return enrolledAt; }
}


